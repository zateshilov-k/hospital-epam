var currentDiagnosisRow = 1;

function prescriptionSubmitButtonListener() {
    var prescriptionTextArea = document.getElementById("prescriptionDescription");
    var prescriptionTypeSelect = document.getElementById("prescriptionType");
    var diagnosisTable = document.getElementById("diagnosis");
    if (prescriptionTextArea.value === "") {
        alert("Введите описание назначения");
    } else {
        $.post("addPrescription", {
            description: prescriptionTextArea.value,
            diagnosisId: diagnosisTable.rows[currentDiagnosisRow].cells[0].innerHTML,
            type: prescriptionTypeSelect.value
        },function (response) {
            updatePrescriptionsTable(diagnosisTable.rows[currentDiagnosisRow].cells[0].innerHTML);
            prescriptionTextArea.value = "";
        });
    }
}

function updatePrescriptionFieldsStyle() {
    var diagnosisTable = document.getElementById("diagnosis");
    var prescriptionFieldSet = document.getElementById("addPrescriptionFieldSet");
    var isDiagnosisOpened = diagnosisTable.rows[currentDiagnosisRow].cells[3].innerHTML;
    if (isDiagnosisOpened === 'false') {
        prescriptionFieldSet.style.display = "none";
    } else if (isDiagnosisOpened === 'true') {
        prescriptionFieldSet.style.display = "inline";
    }
}

function diagnosisSubmitButtonListener() {
    var diagnosisTextArea = document.getElementById("diagnosisDescription");
    var diagnosisTable = document.getElementById("diagnosis");
    var prescriptionFieldSet = document.getElementById("addPrescriptionFieldSet");

    if (diagnosisTextArea.value === "") {
        alert("Введите описание диагноза");
    } else {
        $.post("addDiagnosis", {description: diagnosisTextArea.value}, function (response) {
            diagnosisTextArea.value = "";
            getAndUpdateDiagnosis();
        });
    }
}

function getAndUpdateDiagnosis() {
    $.post("listofdiagnosis", {}, function (response) {
        var diagnosis = JSON.parse(response);
        var diagnosisTable = document.getElementById("diagnosis");
        updateDiagnosisTable(diagnosis, diagnosisTable);
    });
}

function updateDiagnosisTable(diagnosis, table) {
    var diagnosisTable = document.getElementById("diagnosis");
    for (var i = 1; i < table.rows.length; ++i) {
        table.rows[i].innerHTML = "";
    }
    for (var i = 0; i < diagnosis.length; i++) {
        var newRow = table.insertRow(i + 1);
        if ((i+1) === currentDiagnosisRow) {
            setSpecialStyle(newRow);
        } else {
            setDefaultStyle(newRow);
        }
        newRow.addEventListener("click", diagnosisClick);
        for (var j = 0; j < 4; j++) {
            var newCell = newRow.insertCell(j);
            if (j === 0) {
                newCell.innerHTML = diagnosis[i]['diagnosisId'];
            } else if (j === 1) {
                newCell.innerHTML = diagnosis[i]['description'];
            } else if (j === 2) {
                newCell.innerHTML = diagnosis[i]['time'];
            } else if (j === 3) {
                newCell.innerHTML = diagnosis[i]['opened'];
            }
        }
    }
    updatePrescriptionsTable(diagnosisTable.rows[currentDiagnosisRow].cells[0].innerHTML);
    updatePrescriptionFieldsStyle();
}

function diagnosisClick() {
    var rows = this.parentNode.rows;
    for (i = 1; i < rows.length; i++) {
        setDefaultStyle(rows[i]);
    }
    setSpecialStyle(this);
    currentDiagnosisRow = $(this).index();
    var prescriptionFieldSet = document.getElementById("addPrescriptionFieldSet");
    var currentDiagnosisId = this.cells[0].innerHTML;

    updatePrescriptionFieldsStyle();
    var prescriptionSubmitButton = document.getElementById("prescriptionSubmit");
    prescriptionSubmitButton.value = currentDiagnosisId;

    updatePrescriptionsTable(currentDiagnosisId);
}

function setSpecialStyle(row) {
    row.style.color = "red";
}
function setDefaultStyle(row) {
    row.style.color = "black";
}

function updatePrescriptionsTable(diagnosis_id) {
    $.post("listOfPrescriptions", {diagnosisId: diagnosis_id}, function (response) {
         prescriptions = JSON.parse(response);
        var table = document.getElementById('prescriptionsTable');
        for (var i = 1; i < table.rows.length; ++i) {
            table.rows[i].innerHTML = "";
        }
        if (prescriptions.length === 0) {
            table.style.display = "none";
        } else {
            table.style.display = "block";
        }
        for (var i = 0; i < prescriptions.length; i++) {
            var newRow = table.insertRow(i + 1);
            for (var j = 0; j < 5; j++) {
                var newCell = newRow.insertCell(j);
                if (j === 0) {
                    newCell.innerHTML = prescriptions[i]['prescriptionId'];
                } else if (j === 1) {
                    newCell.innerHTML = prescriptions[i]['description'];
                } else if (j === 2) {
                    newCell.innerHTML = prescriptions[i]['type'];
                } else if (j === 3) {
                    newCell.innerHTML = prescriptions[i]['time'];
                } else if (j === 4) {
                    newCell.innerHTML = prescriptions[i]['done'];
                }
            }
        }
    });

}
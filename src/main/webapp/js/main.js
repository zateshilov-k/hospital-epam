var currentDiagnosisRow = 1;
var currentPrescriptionRow = 1;

function prescriptionDoneListener() {
    var prescriptionTable = document.getElementById("prescriptionsTable");
    var diagnosisTable = document.getElementById("diagnosis");
    $.post("doPrescription",
        {prescriptionId : prescriptionTable.rows[currentPrescriptionRow].cells[0].innerHTML}, function () {
        updatePrescriptionsTable(diagnosisTable.rows[currentDiagnosisRow].cells[0].innerHTML);
    });
}

function prescriptionClick() {
    var rows = this.parentNode.rows;
    for (i = 1; i < rows.length; i++) {
        setDefaultStyle(rows[i]);
    }
    setSpecialStyle(this);
    currentPrescriptionRow = $(this).index();

    updatePrescriptionsElementsStyle();
}
function closeDiagnosisButtonListener() {
    var diagnosisTable = document.getElementById("diagnosis");
    $.post("closeDiagnosis", {diagnosisId: diagnosisTable.rows[currentDiagnosisRow].cells[0].innerHTML}, function () {
        getAndUpdateDiagnosis();
    });
}

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
        }, function (response) {
            alert(prescriptionTypeSelect.value);
            updatePrescriptionsTable(diagnosisTable.rows[currentDiagnosisRow].cells[0].innerHTML);
            prescriptionTextArea.value = "";
        });
    }
}

function updateDiagnosisElementsStyle() {
    var diagnosisTable = document.getElementById("diagnosis");
    var closeDiagnosisButton = document.getElementById("closeDiagnosisButton");
    var prescriptionFieldSet = document.getElementById("addPrescriptionFieldSet");

    var isDiagnosisOpened = diagnosisTable.rows[currentDiagnosisRow].cells[3].innerHTML;
    if (isDiagnosisOpened === 'false') {
        prescriptionFieldSet.style.display = "none";
        closeDiagnosisButton.style.display = "none";
    } else if (isDiagnosisOpened === 'true') {
        prescriptionFieldSet.style.display = "inline";
        closeDiagnosisButton.style.display = "inline";
    }
}
function updatePrescriptionsElementsStyle() {
    var doPrescriptionButton = document.getElementById("doPrescription");
    var prescriptionTable = document.getElementById("prescriptionsTable");

    var isPrescriptionDone = prescriptionTable.rows[currentPrescriptionRow].cells[4].innerHTML;
    if (isPrescriptionDone === 'false') {
        doPrescriptionButton.style.display = "inline";
    } else if(isPrescriptionDone === 'true') {
        doPrescriptionButton.style.display = "none";
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
    for (var i = diagnosisTable.rows.length - 1; i > 0; i--) {
        diagnosisTable.deleteRow(i);
    }
    for (var i = 0; i < diagnosis.length; i++) {
        var newRow = table.insertRow(i + 1);
        if ((i + 1) === currentDiagnosisRow) {
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
}

function diagnosisClick() {
    var rows = this.parentNode.rows;
    for (i = 1; i < rows.length; i++) {
        setDefaultStyle(rows[i]);
    }
    setSpecialStyle(this);
    currentDiagnosisRow = $(this).index();
    var currentDiagnosisId = this.cells[0].innerHTML;

    updateDiagnosisElementsStyle();
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
        for (var i = table.rows.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }
        if (prescriptions.length === 0) {
            table.style.display = "none";
        } else {
            table.style.display = "block";
        }
        for (var i = 0; i < prescriptions.length; i++) {
            var newRow = table.insertRow(i + 1);
            newRow.addEventListener("click",prescriptionClick);
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
        updateDiagnosisElementsStyle();
    });

}
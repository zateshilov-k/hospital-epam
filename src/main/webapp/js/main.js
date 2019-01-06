function prescriptionSubmitButtonListener() {
    var prescriptionTextArea = document.getElementById("prescriptionDescription");
    var prescriptionSubmitButton = document.getElementById("prescriptionSubmit");
    var prescriptionTypeSelect = document.getElementById("prescriptionType");
    if (prescriptionTextArea.value === "") {
        alert("Введите описание назначения");
    } else {
        $.post("addPrescription", {description : prescriptionTextArea.value,
            diagnosisId : prescriptionSubmitButton.value,
            type : prescriptionTypeSelect.value});
        prescriptionTextArea.value = "";
        $.post("listOfPrescriptions", {diagnosisId :  prescriptionSubmitButton.value}, function(response) {
            var prescriptions = JSON.parse(response);
            updatePrescriptionsTable(prescriptions);
        });
    }
}



function diagnosisSubmitButtonListener() {
    var diagnosisTextArea = document.getElementById("diagnosisDescription");
    if (diagnosisTextArea.value === "") {
        alert("Введите описание диагноза");
    } else {
        $.post("addDiagnosis",{description : diagnosisTextArea.value});
        diagnosisTextArea.value = "";
        getAndUpdateDiagnosis();
    }

}
function getAndUpdateDiagnosis() {
    $.post("listofdiagnosis",{},function (response) {
        var diagnosis = JSON.parse(response);
        var diagnosisTable = document.getElementById("diagnosis");
        updateDiagnosisTable(diagnosis,diagnosisTable);
    });
}
function updateDiagnosisTable(diagnosis, table) {
    for (var i = 1; i < table.rows.length; ++i) {
        table.rows[i].innerHTML = "";
    }
    for (var i = 0;  i < diagnosis.length; i++) {
        var newRow = table.insertRow(i+1);
        newRow.addEventListener("click", diagnosisClick);
        for (var j = 0; j < 4; j++) {
            var newCell = newRow.insertCell(j);
            if (j === 0 ) {
                newCell.innerHTML=diagnosis[i]['diagnosisId'];
            } else if (j === 1) {
                newCell.innerHTML=diagnosis[i]['description'];
            } else if (j === 2) {
                newCell.innerHTML=diagnosis[i]['time'];
            } else if (j === 3) {
                newCell.innerHTML=diagnosis[i]['opened'];
            }
        }
    }
}
function diagnosisClick() {
    var rows = this.parentNode.rows;
    for (i=1; i < rows.length; i++) {
        rows[i].style.color = "black";
    }
    this.style.color = "red";
    var prescriptionFieldSet = document.getElementById("addPrescriptionFieldSet");
    if (this.cells[3].innerHTML === 'false') {
        prescriptionFieldSet.style.display = "none";
    } else if(this.cells[3].innerHTML === 'true') {
        prescriptionFieldSet.style.display = "inline";
        var prescriptionSubmitButton = document.getElementById("prescriptionSubmit");
        prescriptionSubmitButton.value = this.cells[0].innerHTML;
    }
    $.post("listOfPrescriptions", {diagnosisId : this.cells[0].innerHTML}, function(response) {
        var prescriptions = JSON.parse(response);
        updatePrescriptionsTable(prescriptions);
    });
}
function updatePrescriptionsTable(prescriptions) {
    var table = document.getElementById('prescriptionsTable');
    for (var i = 1; i < table.rows.length; ++i) {
        table.rows[i].innerHTML = "";
    }
    if (prescriptions.length === 0) {
        table.style.display = "none";
    } else {
        table.style.display = "block";
    }
    for (var i = 0;  i < prescriptions.length; i++) {
        var newRow = table.insertRow(i+1);
        for (var j = 0; j < 5; j++) {
            var newCell = newRow.insertCell(j);
            if (j === 0 ) {
                newCell.innerHTML=prescriptions[i]['prescriptionId'];
            } else if (j === 1) {
                newCell.innerHTML=prescriptions[i]['description'];
            } else if( j === 2) {
                newCell.innerHTML=prescriptions[i]['type'];
            } else if (j === 3) {
                newCell.innerHTML=prescriptions[i]['time'];
            } else if (j === 4) {
                newCell.innerHTML=prescriptions[i]['done'];
            }
        }
    }
}
function onClickDelete(id, entity, formId = "mainForm") {
    var mainForm = document.getElementById(formId);
    mainForm.action = "/" + entity + "/" + id + "/delete";
    mainForm.submit();
}

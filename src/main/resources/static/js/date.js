var startDate = $('#startDate');
var endDate = $('#endDate');
var birthday = $('#birthday');

function datepicker(dateField) {
        dateField.datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        lang: 'ru',
        formatDate: 'Y-m-d'
    })
}

datepicker(startDate);
datepicker(endDate);
datepicker(birthday);
var startDate = $('#startDate');
var endDate = $('#endDate');

startDate.datetimepicker({
    timepicker: false,
    format: 'Y-m-d',
    lang: 'ru',
    formatDate: 'Y-m-d',
    onShow: function () {
        this.setOptions({
            maxDate: endDate.val() ? endDate.val() : false
        })
    }
});
endDate.datetimepicker({
    timepicker: false,
    format: 'Y-m-d',
    lang: 'ru',
    formatDate: 'Y-m-d',
    onShow: function () {
        this.setOptions({
            minDate: startDate.val() ? startDate.val() : false
        })
    }
});
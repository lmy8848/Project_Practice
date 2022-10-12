jQuery.validator.addMethod("chinese", function(value, element) {
    let pattern = /^[\u4E00-\u9FA5]{1,5}$/;
    return this.optional(element) || (pattern.test(value));
}, "请正确填写您的名称");

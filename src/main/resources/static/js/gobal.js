
/*
*功能： 模拟form表单的提交
*参数： URL 跳转地址 PARAMTERS 参数
*/
function FormPost(URL, PARAMTERS) {
    //创建form表单
    var temp_form = document.createElement("form");
    temp_form.action = URL;
    //如需打开新窗口，form的target属性要设置为'_blank'
    temp_form.target = "_self";
    temp_form.method = "post";
    temp_form.style.display = "none";
    //添加参数
    for (var item in PARAMTERS) {
        var opt = document.createElement("textarea");
        opt.name = PARAMTERS[item].name;
        opt.value = PARAMTERS[item].value;
        temp_form.appendChild(opt);
    }
    document.body.appendChild(temp_form);
    //提交数据
    temp_form.submit();
}


//集装箱号校验
var checkCntr = function checkCntr (value) {
    //先判断总体长度
    if(value.length!=11){
        return false;
    }
    var getNumber = new Map();
    var num = 10;
    // 生成字母与数字对照表
    for (var i = 0; i < 26; i++) {
        var word = String.fromCharCode((65 + i));
        // 对应码取消了11的倍数，比如11，22，33，所以我们要排除掉
        if (num === 11 || num === 22 || num === 33) {
            num += 1;
        }
        getNumber.set(word, num);
        num += 1;
    }
    var sum = 0;
    // 取四个字母对应的数字，并进行计算
    for (var i = 0; i < 4; i++) {
        sum += getNumber.get(value[i]) * Math.pow(2, i);
    }
    // 对前6个数字进行计算
    for (var i = 4; i < 10; i++) {
        sum += value[i] * Math.pow(2, i);
    }
    console.log(sum);
    // 再对10取余是防止出现校验码为10的情况，假如校验码为10，则取0
    checkDigit = sum % 11 % 10;
    // 判断校验码是否与最后一位数字相等
    if (checkDigit === Number(value[10])) {
        console.log('校验通过')
        return true;
    } else {
        console.log('校验失败')
        return false;
    }

}
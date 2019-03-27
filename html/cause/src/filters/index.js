// 将价格分转为元
export function money(val) {
    val = parseFloat((val + '').replace(/[^\d.-]/g, '')) + ''
    val = keepTwoNum(val, 2).toFixed(2) + ''
    let l = val
        .split('.')[0]
        .split('')
        .reverse()

    let r = val.split('.')[1]
    let t = ''
    for (let i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 === 0 && i + 1 !== l.length ? ',' : '')
    }
    let newstr =
        t
        .split('')
        .reverse()
        .join('') +
        '.' +
        r
    if (newstr.indexOf(',') === 0) {
        newstr = newstr.slice(1, newstr.length)
    }
    if (newstr.indexOf('-') === 0) {
        if (newstr.indexOf(',') === 1) {
            newstr = '-' + newstr.slice(2, newstr.length)
        }
    }
    newstr = '￥' + newstr
    return newstr
}
//     // 两位小数
export const keepTwoNum = (value, fixed) => {
        let pos = value.toString().indexOf('.')
        let decimalPlaces = value.toString().length - pos - 1
        let intPart = value * Math.pow(10, decimalPlaces)
        let divisorA = Math.pow(10, decimalPlaces - fixed)
        let divisorB = Math.pow(10, fixed)
        if (value > 0 && value < 0.01) {
            return 0
        } else {
            return Math.round(intPart / divisorA) / divisorB
        }
    }
    /**
     ** 乘
     **/
export const _mul = (arg1, arg2) => {
    arg1 = Number(arg1)
    arg2 = Number(arg2)
    let m = 0
    let s1 = arg1.toString()
    let s2 = arg2.toString()
    try {
        m += s1.split('.')[1].length
    } catch (e) {}
    try {
        m += s2.split('.')[1].length
    } catch (e) {}
    return (Number(s1.replace('.', '')) * Number(s2.replace('.', ''))) / Math.pow(10, m)
}
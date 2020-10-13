/**
 * Tag 사이값을 찾아 Reverse한다.
 * 
 * @param {string} text     Original 문장
 */
function reverse (text) {
    var itemList = text.split('')
    var tagStart = false
    var startIndex = 0
    var reverseList = []

    for (var i=0; i<itemList.length; i++) {
        var item = itemList[i]
        if (item === '<') {
            tagStart = true
        }

        if (item === '<' && itemList[i + 1] === '/') {
            tagStart = false
            reverseList.push({start: startIndex, end: i})
        } 

        if (item === '>' && tagStart) {
            startIndex = i + 1
        }
    }
    return textReverse(text, reverseList)
}
/**
 * Original 문장을 reverse 한다.
 * 
 * @param {string} text     Original 문장
 * @param {list} list       reverse 할 index list
 */
function textReverse(text, list) {
    var target = text

    for (var j=0; j<list.length; j++) {
        var check = list[j]
        var searchText = text.substring(check.start, check.end)
        var reverseText = searchText.split('').reverse().join('')
        target = target.substr(0, check.start) + reverseText + target.substr(check.end)
    }
    return target
}

import {getInput} from "./input_day2.js";


let input = getInput();
let numberOfSaveReports = 0;

input.forEach((list) => {
    let isOrdered = false;
    if (list[0] > list[1]) {
        isOrdered = isListDescending(list);
    } else if (list[0] < list[1]) {
        isOrdered = isListAscending(list);
    }

    if (isOrdered) {
        numberOfSaveReports++;
    }

})

let numberOfSaveReportsWithDampener = 0;
input.forEach((list) => {
    let isOrdered = isListDescendingWithDampener(list);
    if (!isOrdered) {
        isOrdered = isListAscendingWithDampener(list);
    }

    if (isOrdered) {
        numberOfSaveReportsWithDampener++;
    } else {
        console.log("Failed list: " + list);
    }

})

console.log("Without dampener: " + numberOfSaveReports);
console.log("With dampener: " + numberOfSaveReportsWithDampener);


function isListDescending(list) {

    for (let i = 0; i < list.length - 1 ; i++) {
        if (list[i] <= list[i + 1] || Math.abs(list[i] - list[i + 1]) > 3) {
            return false
        }
    }
    return true;
}

function isListAscending(list) {

    for (let i = 0; i < list.length - 1 ; i++) {
        if (list[i] >= list[i + 1] || Math.abs(list[i] - list[i + 1]) > 3) {
            return false
        }
    }
    return true;
}

function isListDescendingWithDampener(list) {

    let dampenerUsed = false;
    for (let i = 0; i < list.length - 1 ; i++) {
        if (list[i] <= list[i + 1] || Math.abs(list[i] - list[i + 1]) > 3) {

            if (dampenerUsed) {
                return false;
            }
            if ( i === list.length - 2) {
                return true;
            }

            if (i === 0 && list[i + 1] > list[i + 2] && Math.abs(list[i +1] - list[i + 2]) <= 3) {
                i++;
                dampenerUsed = true;
                continue;
            }
            if (i > 0 && list[i-1] >= list[i+1] && Math.abs(list[i-1] - list[i+1]) <= 3) {
                dampenerUsed = true;
                continue;
            }

            if (list[i] <= list[i + 2] || Math.abs(list[i] - list[i + 2]) > 3) {
                return false;
            }

            i++;
            dampenerUsed = true;
        }
    }
    return true;
}

function isListAscendingWithDampener(list) {

    let dampenerUsed = false;
    for (let i = 0; i < list.length - 1 ; i++) {
        if (list[i] >= list[i + 1] || Math.abs(list[i] - list[i + 1]) > 3) {
            if (dampenerUsed) {
                return false;
            }
            if ( i === list.length - 2) {
                return true;
            }
            if (i === 0 && list[i + 1] < list[i + 2] && Math.abs(list[i + 1] - list[i + 2]) <= 3) {
                i++;
                dampenerUsed = true;
                continue;
            }
            if (i > 0 && list[i-1] <= list[i+1] && Math.abs(list[i-1] - list[i+1]) <= 3) {
                dampenerUsed = true;
                continue;
            }
            if (list[i] >= list[i + 2] || Math.abs(list[i] - list[i + 2]) > 3) {

                return false;
            }

            i++;
            dampenerUsed = true;
        }
    }
    return true;
}
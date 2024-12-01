import {getInput} from "./input.js";


let input = getInput();
let list1 = []
let list2 = []

for (let i = 0; i < input.length; i++) {
    list1.push(input[i][0]);
    list2.push(input[i][1]);
}

list1.sort(function(a, b){return a-b});
list2.sort(function(a, b){return a-b})

let distance = 0;
for (let i = 0; i < list1.length; i++) {
    distance += Math.abs(list1[i] - list2[i]);
}

let similarity = 0;
for (let i = 0; i < list1.length; i++) {
    let matches = list2.filter(function(element) { return element === list1[i]}).length;
    similarity += matches * list1[i];
}


console.log(distance);
console.log(similarity);
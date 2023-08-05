// 1. Sa se scrie o functie care calculeaza suma tuturor "weight"-urilor din structura de date data
//    Pentru exemplul de mai sus suma o sa fie 7+3+9+15+22+41+23+13 = 133
//    Se cere rezolvarea in doua moduri (fara biblioteci de la 0)

import { dataStructure } from "./data-structure.js";

function calculateSumOfWeights(data) {
  if (!data) return 0;

  let sum = data.weight;

  if (Array.isArray(data.children)) {
    data.children.forEach((child) => {
      sum += calculateSumOfWeights(child);
    });
  }

  return sum;
}

const sumOfWeights = calculateSumOfWeights(dataStructure);
console.log("Method 1: " + sumOfWeights);

function calculateSumOfWeightsNonRecursive(data) {
  let sum = 0;
  const stack = [data];

  while (stack.length > 0) {
    const currentNode = stack.pop();
    sum += currentNode.weight;

    if (Array.isArray(currentNode.children)) {
      stack.push(...currentNode.children);
    }
  }

  return sum;
}

const sumOfWeights2 = calculateSumOfWeightsNonRecursive(dataStructure);
console.log("Method 2: " + sumOfWeights2);

// 2. Sa se scrie o functie care returneaza un object care va contine calea ce are suma maxima
//     Pentru exemplul de mai sus acest obiect ar arata asa: { maxPath: "A->C->K->J", sumWeight: 84 /*(7+41+23+13)*/ }
// Observatie: structura de date de mai sus este doar un exemplu, algoritmii ceruti la tema trebuie sa mearga pentru
// orice structuri de date de acest format, indiferent de nivelul de adancime al acestora

function findMaxSumPath(data) {
  if (!data) return { maxPath: "", sumWeight: 0 };

  let maxPath = data.name;
  let maxSumWeight = data.weight;

  if (Array.isArray(data.children)) {
    data.children.forEach((child) => {
      const { maxPath: childPath, sumWeight: childSum } = findMaxSumPath(child);
      const currentSum = data.weight + childSum;
      if (currentSum > maxSumWeight) {
        maxSumWeight = currentSum;
        maxPath = data.name + "->" + childPath;
      }
    });
  }

  return { maxPath, sumWeight: maxSumWeight };
}

const maxSumPath = findMaxSumPath(dataStructure);
console.log(maxSumPath);

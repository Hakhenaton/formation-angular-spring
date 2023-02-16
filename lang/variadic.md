# Variadics

Si on veut passer une liste d'arguments à une fonction, on pourrait penser à faire :

```ts
function printStrings(strings: string[]){
    for (const s of strings){
        console.log(s)
    }
}

printStrings(['one', 'two', 'three']) // affiche one, puis two, puis three 
```

Dans plein de cas, c'est suffisant, mais parfois on veut écrire le liste d'arguments en mode positionnel.

On parle alors de fonction "variadique":

```ts
function printStrings(...strings: string[]){
    for (const s of strings){
        console.log(s)
    }
}

printStrings('one', 'two', 'three') // un tableau est créé implicitement ici
```

Beaucoup de fonctions sont variadiques, par exemple `console.log()`:

```ts
console.log('un message', 'un autre message', 'et encore un autre message')
```

Lorsqu'on dispose d'un tableau et qu'on désire le passer à une fonction variadique, on peut utiliser le spread operator:

```ts
const valuesToDisplay = [
    'un message',
    'un autre message',
    'et encore un message'
]
console.log(...valuesToDisplay)
```
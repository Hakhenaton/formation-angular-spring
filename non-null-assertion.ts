class MyClass {
    // force le compilateur a penser que `foo` est initialisé à la construction d'une instance de `MyClass`;
    // on force donc du type `string|undefined` à `string`
    foo!: string

    // dit au compilateur que cette propriété est potentiellement absente.
    // le type sera donc `string|undefined`.
    // en effet: 
    //  - si la propriété n'existe pas, un accès renverra systématiquement `undefined`
    //  - si la propriété existe, elle est potentiellement `undefined`, sinon de type `string`
    bar?: string

    constructor(){}
}

const c = new MyClass()
// vu que `bar` est optionnel, on doit faire une vérification de l'existence de la propriété.
if (c.bar){
    console.log(c.bar.length)
}
// ici, cette ligne va provoquer une erreur de type `cannot read property length of undefined`.
// on a menti au compilateur en déclarant la propriété avec `!` sans l'initialiser d'une quelconque manière.
// l'accès à `c.foo` va renvoyer `undefined`, et un accès à `length` sur `undefined` va échouer.
console.log(c.foo.length)
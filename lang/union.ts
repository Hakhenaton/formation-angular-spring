type FirstType = {
    type: 1
    foo: string
}

type SecondType = {
    type: 2
    bar: boolean
}

// UnionType correspond soit à FirstType, soit à SecondType
type UnionType = FirstType | SecondType

function useUnionType(f: UnionType) {

    // console.log(f.foo) // ne compile pas à ce stade car le compilateur n'a pas moyen d'inférer le type de `f` entre `FirstType` et `SecondType`.
    // console.log(f.bar) // pareil 

    console.log(f.type) // la seule propriété commune est affichable, à ce stade `f.type` est de type `1|2`.

    switch (f.type) {
        // dans ce cas le compilateur infère le type vers `FirstType`
        case 1:
            console.log(f.foo)
            break
        // dans ce cas le compilateur infère le type vers `SecondType`
        case 2:
            console.log(f.bar)
            break
        // on note qu'aucun `default` case n'est à gérer car le compilateur considère que tous les cas de notre union type ont été traités.
    }
}

useUnionType({ type: 1, foo: 'hello world!' })
useUnionType({ type: 2, bar: true })
// useUnionType({ type: 3 }) // ne compile pas car `type` correspond soit à 1, soit à 2.
// useUnionType({ type: 1, bar: true }) // ne compile pas car `bar` correspond à `SecondType`, pas `FirstType`
// useUnionType({ type: 2 }) // ne compile pas car `bar` est absent.



enum UserRole {
    User,
    Moderator,
    Administrator
}

for (const role of Object.values(UserRole)){
    
}
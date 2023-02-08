# Types

Déclarer des types en TS peut se faire de différentes manières:

## Interfaces

Les interfaces en TS servent à déclarer des types sous forme d'objets:

```ts
interface MyType {
    foo: number
    bar: string
    baz: {
        foo: number
        bar: string
    }
    func: () => void
}
```

On peut étendre une interface existante, un peu comme si c'était une classe:

```ts
interface MyComplexType extends MyType {
    extra: boolean
}
```

On peut aussi rajouter du contenu dans le type à un autre endroit du code, ce mécanisme n'est pas privilégié car très invasif:

```ts
interface MyType {
    anotherExtra: string
}
```

Les interfaces servent également à déclarer des contrats programmatiques. Mais c'est une autre partie du cours.

## Types

Le mot clé `type` sert aussi à contenir des types quelconques, sous forme de variable (ou alias).

C'est très pratique car on peut construire des types complexes et les mettre derrière un nom simple.

```ts
type MyType = {
    foo: number
    bar: string
    baz: {
        foo: number
        bar: string
    }
    func: () => void
}
```

On peut y mettre autre chose qu'un objet, contrairement aux interfaces.

```ts
type ConstantValue = 'foo' // ConstantValue est un type correspondant à une valeur constante de type string ("foo").
```
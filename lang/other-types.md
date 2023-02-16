# Types importants en TS

`any` correspond à *n'importe quel type*, c'est un passe-partout qui va contourner toutes les restrictions de Typescript. On ne l'utilise pas normalement.

Si on est pas sûr d'un type ou qu'on ne peut pas connaître le type à l'avance, on utilise `unknown`

```ts
function getSomeUnknownData(): unknown {
    return 1
}

const result = getSomeUnknownData()

// on est obligés de vérifier le type
if (typeof result === 'number'){
    console.log(Math.sqrt(result))
}
```

Le type `never` est utilisé pour symboliser un type qui ne doit jamais être retourné.

```ts

function infinite(): never {
    while (true){}
}

// le compilateur sait que infinite ne retourne jamais.
infinite()
```

```ts
function crash(): never {
    throw Error('boom')
}

// le compilateur sait que crash() ne retourne jamais
crash()
```

L'indicateur `readonly` sert à empêcher l'assignation multiple sur une propriété d'objet.

On le mettra systématiquement par défaut.

```ts
type StringObject = {
    readonly foo: string
    readonly bar: string
    readonly baz: string
}
```

Devant un type de tableau, `readonly` indique que le tableau est lui-même non mutable.

```ts
type MonType = {
    readonly tab: readonly string[]
}

const p: MonType = {
    tab: ['one', 'two', 'three']
}
p.tab = [] // ne compile pas à cause du premier readonly
p.tab.push('four') // ne compile pas à cause du deuxième readonly
```

Il est possible de récupérer un union type sur les différentes clés existantes dans un type d'objet.

```ts
type KeyOfStringObject = keyof StringObject // "foo"|"bar"|"baz"
```

Il est possible de mapper un type d'objet vers un autre en reprenant les noms de clés.

Notez l'utilisation du type standard `Omit` pour enlever la propriété `baz` de l'objet d'origine.

```ts
type BooleanObject = {
    [Key in keyof Omit<StringObject, 'baz'>]: boolean
}
```

`Record<K, V>` est un type utilitaire servant à déclarer des objets avec des clés dynamiques et non connues à l'avance.

```ts
type MonRecord = Record<string, boolean>
type AutreFaconDeLeFaire = { [s: string]: boolean }

const b: MonRecord = {
    prop1: true,
    prop2: false
}

const a: AutreFacon = {
    prop1: true,
    prop2: false
}
```

Les tuples sont des types correspondants à des tableau de taille fixe dont les valeurs contenues ont des types connus à l'avance.

```ts
type MyTuple = [number, string]

const t: MyTuple = [1, '']
```
console.log('hello world!')

function foo(a: string){
    console.log(a)
}

console.log(foo('bonjour'))

interface MyType {
    foo: number
    bar: string
    baz: {
        foo: number
        bar: string
    }
    func: () => void
}

interface MyComplexType extends MyType {
    extra: boolean
}

interface MyType {
    anotherExtra: string
}

const myType: MyType = {
    anotherExtra: '',
    foo: 1,
    bar: 'foo',
    baz: {
        foo: 1,
        bar: 'hello'
    },
    func: () => {}
}

type MySecondType = MyType

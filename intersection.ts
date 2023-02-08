type FirstType = {
    foo: string
}

type SecondType = {
    bar: string
}

type IntersectionType = FirstType & SecondType // { foo: string, bar: string }
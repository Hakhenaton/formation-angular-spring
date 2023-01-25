let i = 0

// callback hell
setTimeout(() => {
    i++
    setTimeout(() => {
        i++
        setTimeout(() => {
            i++
        }, 3000)
    }, 2000)
}, 1000)

for (let y = 0; y < 1000000000; y++){
    
}

console.log(i)

const promise = fetch('https://google.com')

promise

.then(response => {
    return fetch('https://amazon.com')
})

.then(response => {
    return fetch('https://twitter.com')
})

.catch(err => {
    console.error(err)
})

async function myAsyncTasks(){

    try {
        const googleResponse = await fetch('https://google.com')
    } catch(err){
        return false
    }
    const amazonResponse = await fetch('https://amazon.com')
    const twitterResponse = await fetch('https://twitter.com')

    return true
}

myAsyncTasks()

    .then(res => {

    })

    .catch(err => {
        console.error(err)
    })


let variable = 1 // number
variable = 'foo' // string
variable = undefined // undefined
variable = {} // object
variable = false // boolean

console.log(typeof typeof variable)

instanceof


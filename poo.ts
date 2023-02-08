// Ce qu'on fait en POO...
namespace PooMode {
    class Dog {
        breed: string = 'labrador'
        
        talk(){
            console.log('waf waf, je suis un ' + this.breed)
        }
    }
    
    const dog = new Dog()
    dog.talk()
}

// ... est juste une autre façon d'écrire du code.
namespace ProceduralMode {

    type Dog = {
        breed: string
    }

    function talk(dog: Dog){
        console.log('waf waf, je suis un ' + dog.breed)
    }

    const dog = {
        breed: 'labrador'
    }

    talk(dog)
}
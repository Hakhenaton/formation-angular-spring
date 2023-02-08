// Exemple d'héritage en Typescript

// Dans ce namepace, on fait un exemple avec de l'héritage pur.
namespace WithInheritance {
    
    // Cette classe est "abstract", ce qui veut dire qu'elle n'est pas instanciable.
    // Une classe abstraite est faite uniquement pour regrouper des comportements communs à plusieurs classes
    // sans pour autant qu'elle représente un objet réel (concret) dans notre application.
    abstract class Animal {

        // L'indicateur de visibilité `private` sert à rendre invisible la propriété depuis l'extérieur de la classe.
        // Ca devrait être le choix par défaut pour toutes les propriétés de nos classes.
        // `protected` sert à rendre l'attribut visible uniquement dans la classe ainsi que dans les classes filles.
        // `public` ou une absence d'indicateur de visibilité rend la propriété visible depuis l'ensemble du programme.
        private readonly specie: string

        constructor(specie: string){
            this.specie = specie
        }

        // les deux bloc supérieurs peuvent être simplifiés comme ceci:
        // constructor(private readonly specie: string){}
    
        eat(){
            console.log(`i'm a ${this.specie} and i eat like every other animals !`)
        }
    }
    
    // Une class concrète qui hérite de Animal
    class Dog extends Animal {
    
        private readonly breed: string

        constructor(breed: string){
            super('canis familiaris')
            this.breed = breed
        }

        bark(){
            console.log(`woof, i'm a ${this.breed} dog !`)
        }
    }
    
    // Une deuxième classe concrète qui hérite de Animal.
    class Cat extends Animal {
    
        private readonly hairType: string

        constructor(hairType: string){
            super('felis catus')
            this.hairType = hairType
        }
    
        meow(){
            console.log(`meow, i'm a ${this.hairType} cat !`)
        }
    }
    
    
    const dog = new Dog('yorkshire')
    dog.eat() // affiche "i'm a canis familiaris and i eat like every other animals !"
    dog.bark() // affiche "woof, i'm a yorkshire dog !"

    const cat = new Cat('fluffy')
    cat.eat() // affiche "i'm a felis catus and i eat like every other animals !"
    cat.meow() // affiche "meow, i'm a fluffy cat !0"

    // const animal = new Animal()  // Cette ligne ne compile pas car Animal n'est pas instanciable.

    /*  
        Cependant, on peut manipuler des chats et des chiens en tant qu'Animals.
        Le cast n'est pas obligatoire mais par défaut le tableau déclaré ici est de type Dog|Cat. 
    */
    const animals = [dog, cat] as Animal[]
    for (const animal of animals){
        animal.eat()
    }

}

// Dans ce namespace, on présente le même fonctionnement mais sans utiliser l'héritage avec `extends`.
// On utilise la composition.
namespace WithComposition {

    // Plutôt que d'avoir une classe mère, on définit une classe qui va représenter
    // des comportements communs entre tous les animaux.
    class AnimalBehavior {
        
        private readonly specie: string

        constructor(specie: string){
            this.specie = specie
        }
    
        eat(){
            console.log(`je suis un(e) ${this.specie} et je mange comme tous les animaux !`)
        }
    }

    // Le type en commun sera alors représenté par une interface.
    // Chaque classe d'animal va devoir implémenter cette interface.
    // On notera que contrairement à Java, l'absence d'implémentation explicite sur les types 
    // manipulés (avec le mot clé `implements`) ne provoque pas d'erreur tant que ces derniers sont
    // sont compatibles et comportent les méthodes/propriétés présentes dans l'interface.
    // On pourrait donc très bien se passer d'utiliser `implements Animal` dans Cat et Dog, mais on
    // doit se forcer à le faire dès que possible pour plus de clarté et pour symboliser l'intention
    // du développeur.
    interface Animal {
        eat(): void
    }

    class Dog implements Animal {

        private readonly behavior = new AnimalBehavior('canis familiaris')

        constructor(private readonly breed: string){}
        
        eat(): void {
            this.behavior.eat()
        }

        bark(){
            console.log(`woof, i'm a ${this.breed} dog !`)
        }
    }

    class Cat implements Animal {

        private readonly behavior = new AnimalBehavior('felis catus')

        constructor(private readonly hairType: string){}
        
        eat(): void {
            this.behavior.eat()
        }

        meow(){
            console.log(`meow, i'm a ${this.hairType} cat !`)
        }
    }

    const dog = new Dog('yorkshire')
    const cat = new Cat('fluffy')

    // Comme pour le premier exemple avec l'héritage, on peut effectuer un cast.
    // On veut manipuler nos deux valeurs en tant que `Animal`, pas en tant que `Cat|Dog`. 
    // De cette manière, on symbolise mieux l'intention voulue.
    const animals = [dog, cat] as Animal[]
    for (const animal of animals){
        animal.eat()
    }
}


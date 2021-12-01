Curtis Klomegan  
Arthur Lalande-Marchand  

# Rapport TP3 : A la découverte de String

#### Ressource: https://github.com/barais/Spring2020
#### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;https://hackmd.diverse-team.fr/s/rJKt4lSND#TPs

## Récupérer le projet

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pour récupérer le travail effectué par notre binôme, vous pouvez télécharger le projet sur la branche `master` sur le github: https://github.com/ArthurLM35/jpa.spring

## Le but, les technologies et le lancement

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Le but du TP était la réalisation d'une application ressemblant à Doctolib. Il permet de prendre des rendez-vous entre un client et un professionnel.                      
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pour la réalisation de notre TP, nous avons utilisé le framework Sprint Boot. Pour stocker nos informations, nous avons utilisé la base de données HSQLDB. Pour finir, il fallait avoir la possibilité de créer un front pour produire une vue Web. Le template Thymeleaf a été utilisé.


## Nos classes

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nous avons décider d'avoir trois entités pour représenter cette action. Notre première entité, User, représente la création d'un utilisateur. Elle possède plusieurs atributs: l'attribut id, clef primaire de notre entité, l'attribut name, l'attribut mail puis l'attribut mdp. La deuxième entité nommée Worker. Elle hérite de la classe User. Elle permet de différencier si un utilisateur est un client ou un professionnel. Pour cela, elle possède un attribut job qui fait réference à son travail. Notre dernière entité correspond à un rendez-vous. Elle s'apelle Appointment. Grâce à ses 5 attributs: l'attribut id, clef primaire de notre classe, l'attribut date, l'attibut lenght, l'attribut us qui correponds à un User (notre client), l'attribut work qui corresponds à un Worker (notre professionnel) puis un attribut description. 

## Fonctionnement du front

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pour le fonctionnement de notre front, nous avons crée six documents HTML. Le premier `page1.html` corresponds à la page d'accueil de notre application. Elle contient trois bouttons: l'inscription d'un client `inscription2.html`, l'inscription d'un professionnel `inscription.html` et la connexion `connection.html`. Les inscriptions demandent à l'utilisateur son adresse mail, son mot de passe, le prénom/nom et le travail pour les professionnel. Si l'adresse mail est déjà utilisé, l'inscription n'est pas possible. Après avoir effectué votre inscription, vous pouvez enfin vous connectez. Vous arrivez sur votre page personnelle `mypage.html` contenant vos informations de rendez-vous dans un tableau. L'application fournit un bouton pour prendre un rendez-vous `rdv.html`. Pour effectué la demande, il faut dans un premier temps choisir le médecin que vous voulez pour la consultation puis le validé. Il faudra par la suite selectionner la date, le créneau, et la description. Si le créneau n'est pas dans les horaires de rendez-vous, par exemple, 10h10 au lieu de 10h00, il sera alors proposer un rendez-vous à 10 heures au client.
Le client ou le médecin ont la possibilité d'annuler un rendez-vous. 

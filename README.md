# TP: Etat (State design pattern)

## Quoi de neuf dans ce code :

- Ajout de deux classes **WatchViewer** et **ButtonViewer**
- Ajout d’une nouvelle interface **LookupListener** qui permettrait de rendre l'instance du **Lookup** Observable
- La classe **Launcher** lance deux instances du **WatchViewer** et une instance pour les boutons

## Travail demandé

1) Programmer le bouton set pour qu’il affiche les secondes sur la montre. Constatez qu’il n’existe qu’un seul bouton ***set*** pour les deux Montres. Afin de déterminer laquelle des deux montres sera affectée par l’action du boutons, vous allez rajouter une référence de la montre sélectionnée au Lookup. Le bouton n’aura donc qu’aller chercher dans le Lookup la référence qui va être affectée.

2) Faire fonctionner les deux boutons ***« set »*** et ***« mode »*** sur les deux montres en utilisant le design pattern « Etat ».

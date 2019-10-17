Author: Kaan Çakmak
Project: Okey Board Game
Date: 17.10.2019

Project contains 4 main modules such as Main, Player, Tile and Controller modules.
Player module includes properties of player objects.
Tile module includes properties of rummy tile.
Controller module is the module which controls backend of the all project.
Main module for run the project.

Project creates 4 players and 106 rummy stones(includes coppies and fake jokers).
Selects indicator and and removes that (one indicator left at game).
According to indicator, picks 2 jokers.
Change jokers number to -1 and fake jokers(52) to actualy joker number(indicator+1).
Shuffle and distribute tiles. Randomly 1 player gets 15 tiles and others get 14 tiles.
Project calculates double score, same number sequence and same color sequence.
Normal score consist of same number and color sequences.

Normal score = Max(scoreOfColorSequence||scoreOfNumberSequence) + Min(scoreOfColorSequence||scoreOfNumberSequence)/2

Double Score = Double count * 2

Both normal and double scores contains sequences with joker (if possible)

Program selects double or normal score which is higher as total player score.

According to player scores, program selects the winner player and display its detailed informations.

Displayed information:
Indicator:
Joker:
Winner:
Double score:
All tiles in ascending order:
Clustered according to number:
Total number clustering score:
Clustered according to color:
Total color clustering score:
Final score of Player:
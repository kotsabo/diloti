package com.example.kotsabo.myapplication

class Game {

    var firstPlayer: Player = Player()
    var secondPlayer: Player = Player()
    var rounds: Int = 0

    fun updateGame(points1: Int, points2: Int, multiplier1: Int, multiplier2: Int) {
        this.rounds += 1
        this.firstPlayer.addPoints(points1, multiplier1)
        this.secondPlayer.addPoints(points2, multiplier2)
        this.firstPlayer.saveRoundPoints()
        this.secondPlayer.saveRoundPoints()
    }

}
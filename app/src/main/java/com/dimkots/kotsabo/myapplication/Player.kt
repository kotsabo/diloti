package com.dimkots.kotsabo.myapplication

class Player {
    var name: String = "Player 1"
    private var points: Int = 0
    var arrayPoints = ArrayList<Int>()

    fun addPoints(roundPoints: Int, multiplier: Int) {
        this.points += roundPoints + multiplier * 10
    }

    fun saveRoundPoints() {
        this.arrayPoints.add(this.points)
    }

}
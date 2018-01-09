package com.despegar.sem.agendetect.utils

object MathUtils {

  def average(s: Seq[Int]): Double = s.foldLeft((0.0, 1)) ((acc, i) => ((acc._1 + (i - acc._1) / acc._2), acc._2 + 1))._1


  def variance(sec:Seq[Int]):Double =

  {

    sec.map(_.toDouble).map(x => math.pow(x - average(sec), 2)).sum / sec.size

  }

}

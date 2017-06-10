package edu.holycross.shot.script


import edu.holycross.shot.cite._

import scala.scalajs.js
import js.annotation.JSExport



@JSExport  case class Script (
  urn: Cite2Urn,
  label: String,
  alphabetic: Vector[Int],
  numeric: Vector[Int],
  punctuation: Vector[Int],
  editorial: Vector[Int]
)  {


  //def
}

object Script {
  def apply(urn: Cite2Urn,
  label: String,
  alphabetic: String,
  numeric: String = "",
  punctuation: String = "",
  editorial: String = ""): Script = {
    val alpha = Vector[Int]()
    val num = Vector[Int]()
    val punct = Vector[Int]()
    val editor = Vector[Int]()
    Script(urn,label,alpha,num,punct,editor)
  }
}

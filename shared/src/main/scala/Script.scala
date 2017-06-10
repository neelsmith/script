package edu.holycross.shot.script


import edu.holycross.shot.cite._

import scala.scalajs.js
import js.annotation.JSExport



@JSExport  case class Script (
  urn: Cite2Urn,
  label: String,
  alphabetic: Set[Int],
  numeric: Set[Int],
  punctuation: Set[Int],
  editorial: Set[Int])  {


  /** True if codepoint is alphabetic.
  *
  * @param codepoint Codepoint to test.
  */
  def isAlphabetic(codepoint: Int)= {
    alphabetic.contains(codepoint)
  }


  /** True if all codepoints in s are alphabetic.
  *
  * @param s String to test.
  */
  def isAlphabetic(s: String)= {
    val codepointSet = Script.codepointsForString(s).toSet
    codepointSet subsetOf alphabetic
  }


  /** True if codepoint is numeric.
  *
  * @param codepoint Codepoint to test.
  */
  def isNumeric(codepoint: Int)= {
    numeric.contains(codepoint)
  }

  /** True if codepoint is punctuation.
  *
  * @param codepoint Codepoint to test.
  */
  def isPunctuation(codepoint: Int)= {
    punctuation.contains(codepoint)
  }

  /** True if codepoint is editorial.
  *
  * @param codepoint Codepoint to test.
  */
  def isEditorial(codepoint: Int)= {
    editorial.contains(codepoint)
  }
}

object Script {


  /** Convert a string to a list of Unicode codepoints.
  *
  * @param s String to convert.
  * @param idx Index of codepoints to count from.
  * @param codepoints List of previously seen codepoints.
  */
  def codepointsForString (s: String, idx : Int = 0, codepoints: List[Int] = List[Int]()): List[Int] = {
  if (idx >= s.length) {
    codepoints.reverse
  } else {
    val cp = s.codePointAt(idx)
    codepointsForString(s, idx + java.lang.Character.charCount(cp), cp :: codepoints)
  }
}

  def apply(urn: Cite2Urn,
  label: String,
  alphabetic: String,
  numeric: String = "",
  punctuation: String = "",
  editorial: String = ""): Script = {
    val alpha = Set[Int]()
    val num = Set[Int]()
    val punct = Set[Int]()
    val editor = Set[Int]()
    Script(urn,label,alpha,num,punct,editor)
  }
}

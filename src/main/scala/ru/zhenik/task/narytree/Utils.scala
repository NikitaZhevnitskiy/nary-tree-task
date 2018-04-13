package ru.zhenik.task.narytree


import scala.io.Source

object Utils extends JsonSpanProtocol {

  import spray.json._
  val filename1 = "src/main/resources/spanExample.json"

  // read from file (Unchecked)
  def spanFromFile(filename: String): Span = {
    JsonParser(Source.fromFile(filename).getLines().mkString)
      .convertTo[Span]
  }


  //                        1A
  //                     /   |   \
  //                   2B    5E   7G
  //                  / \     \
  //                3C   4D    6F
  def getSpanListWith7Nodes(): List[Span] = {
    val span = Utils.spanFromFile(filename1)

    val span01 = span.copy(startTime = 1, operationName = "A", spanId = "1", parentId = "0")
    val span11 = span.copy(startTime = 2, operationName = "B", spanId = "2", parentId = "1")
    val span12 = span.copy(startTime = 5, operationName = "E", spanId = "5", parentId = "1")
    val span13 = span.copy(startTime = 7, operationName = "G", spanId = "7", parentId = "1")
    val span21 = span.copy(startTime = 3, operationName = "C", spanId = "3", parentId = "2")
    val span22 = span.copy(startTime = 4, operationName = "D", spanId = "4", parentId = "2")
    val span23 = span.copy(startTime = 6, operationName = "F", spanId = "6", parentId = "5")

    val spanList = List(span23, span22, span21, span13, span12, span11, span01)
//    println(s"startTime : operationName : spanId : parentId")
//        spanList.foreach(s => println(s"${s.startTime}: ${s.operationName} : ${s.spanId} : ${s.parentId}"))
    spanList
  }

  //                        1A
  //                     /   |   \
  //                   2B    5E   7G
  //                  /
  //                3C
  def getSpanListWith5Nodes(): List[Span] = {
    val span = Utils.spanFromFile(filename1)

    val span01 = span.copy(startTime = 1, operationName = "A", spanId = "1", parentId = "0")
    val span11 = span.copy(startTime = 2, operationName = "B", spanId = "2", parentId = "1")
    val span12 = span.copy(startTime = 5, operationName = "E", spanId = "5", parentId = "1")
    val span21 = span.copy(startTime = 3, operationName = "C", spanId = "3", parentId = "2")
    val span13 = span.copy(startTime = 7, operationName = "G", spanId = "7", parentId = "1")

    val spanList = List(span21, span12, span11, span01, span13)
    //      println(s"startTime : operationName : spanId : parentId")
    //      spanList.foreach(s => println(s"${s.startTime}: ${s.operationName} : ${s.spanId} : ${s.parentId}"))
    spanList
  }

  //                      1A
  //                     /
  //                   2B
  //                  /
  //                3C
  def getSpanListWith3Nodes(): List[Span] = {
    val span = Utils.spanFromFile(filename1)

    val span01 = span.copy(startTime = 1, operationName = "A", spanId = "1", parentId = "0")
    val span11 = span.copy(startTime = 2, operationName = "B", spanId = "2", parentId = "1")
    val span21 = span.copy(startTime = 3, operationName = "C", spanId = "3", parentId = "2")

    val spanList = List(span21, span11, span01)
    //      println(s"startTime : operationName : spanId : parentId")
    //      spanList.foreach(s => println(s"${s.startTime}: ${s.operationName} : ${s.spanId} : ${s.parentId}"))
    spanList
  }
}

object App extends App with JsonSpanProtocol {
  import spray.json._

  val list: List[Span] = Utils.getSpanListWith5Nodes()
  println(TreeBuilder.build(list).toJson)
}

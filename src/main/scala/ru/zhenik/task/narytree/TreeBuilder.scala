package ru.zhenik.task.narytree


object TreeBuilder {

  // TODO
  def build(): SpanTree = ???
}

object App extends App with JsonSpanProtocol {
  import spray.json._

  val spans = Utils.getSpanListWith4Nodes()
  // check here http://json.parser.online.fr/
  println(spans.toJson)


}

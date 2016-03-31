package example

import japgolly.scalajs.react.ReactComponentB

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom

import scalatags.JsDom.{attrs => attr, styles => style}
import scalatags.JsDom.tags._
import scalatags.JsDom.implicits._
import scalatags.DataConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.vdom.prefix_<^._

case class StateCounter(counter: Int)

class CounterBackend($ : BackendScope[_, StateCounter]) {
  def incrementCounter = {
    $.modState(s => s.copy(s.counter + 1))
  }

  def render(state: StateCounter) = {
    <.div(
        <.div(
            <.span(state.counter)
        ),
        <.button(
            ^.onClick --> incrementCounter,
            "Increment!"
        )
    )
  }
}

object ScalaJSExample extends js.JSApp {
  def main(): Unit = {

    val c = ReactComponentB[Unit]("Counter")
      .initialState(StateCounter(0))
      .renderBackend[CounterBackend]
      .buildU

    val rootNode = dom.document.getElementById("playground")

    ReactDOM.render(c(), rootNode)
  }

  def square(x: Int): Int = {
    x * x
  }
}

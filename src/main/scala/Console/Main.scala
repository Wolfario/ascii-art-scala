package Console
import Application.Application

object Main extends App {

  var consoleInput: InputHandler = new InputHandler
  consoleInput.handle(args)

  var app = new Application

  app.setImage(consoleInput.getImage)
  app.setFilters(consoleInput.getFilters)
  app.setTable(consoleInput.getTable)
  app.setTranslationType(consoleInput.getTranslationType)
  app.setOutput(consoleInput.getOutput)

  app.handle()
}
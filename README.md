# Logger

Simple Logger Plugin +API with Events.
This Plugin does logging stuff, which will be saved in
`.log` files, in the directory of your choice.
You can also specify your own events in the config,
that should be logged. The event (cancellable), which is
called before everything is going to be logged,
is named `LogEvent`. If you want to make your own Logger,
just extend instead of the `Listener` class, the class
called `LogListener`. If you now have your logging text,
just call the parent method `LogListener#log`. You can
specify an extra level, but you don't have to.
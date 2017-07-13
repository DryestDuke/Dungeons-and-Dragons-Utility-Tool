from kivy.app import App
from kivy.uix.gridlayout import GridLayout
from kivy.uix.label import Label


class IntroScreen(GridLayout):
    def __init__(self, **kwargs):
        super(IntroScreen, self).__init__(**kwargs)
        self.add_widget(Label(text='Welcome to Nicholas Walter\'s D&D Utility Tool!'))
		

		
class MyApp(App):
    def build(self):
        return IntroScreen()


if __name__ == '__main__':
    MyApp().run()
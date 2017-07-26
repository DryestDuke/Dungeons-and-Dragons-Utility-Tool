from appJar import gui

#initializing application
app = gui()

app.setTitle("D&D Utility Tool")
app.setGeometry("300x300")

#setting up widgets
fList = ["NPCs", "Buildings", "Village", "Rumors", "Encounters", "Loot", "XP Budget Calculator", "Combat Encounter Generator", "Monster Lists", "Wild Magical Effect", "Print a List", "Spell Cards"]
app.addListBox("functionsList", fList)
app.setListBoxRows("functionsList", len(fList))

#setting up basic functionality - via "Proceed" button
choice = 0

def proceed(choice):
	choice_str = app.getListItems("functionsList")[0]
	if choice_str == "NPCs":
		choice = 1
	elif choice_str == "Buildings":
		choice = 2
	elif choice_str == "Village":
		choice = 3
	elif choice_str == "Rumors":
		choice = 4
	elif choice_str == "Encounters":
		choice = 5
	elif choice_str == "Loot":
		choice = 6
	elif choice_str == "XP Budget Calculator":
		choice = 7
	elif choice_str == "Combat Encounter Generator":
		choice = 8
	elif choice_str == "Monster Lists":
		choice = 9
	elif choice_str == "Wild Magical Effect":
		choice = 10
	elif choice_str == "Print a List":
		choice = 11
	elif choice_str == "Spell Cards":
		choice = 12
		
app.addButton("Proceed", proceed)

#starts app
app.go()
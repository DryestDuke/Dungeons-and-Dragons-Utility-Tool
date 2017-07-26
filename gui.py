from appJar import gui

#initializing application
app = gui()

app.setTitle("D&D Utility Tool")
app.setGeometry("300x300")

#setting up widgets
fList = ["NPCs", "Buildings", "Village", "Rumors", "Encounters", "Loot", "XP Budget Calculator", "Combat Encounter Generator", "Monster Lists", "Wild Magical Effects", "Print a List", "Spell Cards"]
app.addListBox("functionsList", fList)
app.setListBoxRows("functionsList", len(fList))

#setting up proceed button
def proceed(choice):
	choice_list = app.getListItems("functionsList")
	if len(choice_list) < 1:
		print("Nothing was chosen.")
		return
	choice = choice_list[0]
	if choice == "NPCs":
		runNPCs()
	elif choice == "Buildings":
		runBuildings()
	elif choice == "Village":
		runVillage()
	elif choice == "Rumors":
		runRumors()
	elif choice == "Encounters":
		runEncounters()
	elif choice == "Loot":
		runLoot()
	elif choice == "XP Budget Calculator":             
		runXPBudgetCalculator()
	elif choice == "Combat Encounter Generator":
		runCombatEncounterGenerator()
	elif choice == "Monster Lists":
		runMonsterLists()
	elif choice == "Wild Magical Effects":
		runWildMagicalEffects()
	elif choice == "Print a List":
		runPrintAList()
	elif choice == "Spell Cards":
		runSpellCards()
	else:
		print("Choice/s: ", app.getListItems("functionsList"))
		
app.addButton("Proceed", proceed)

#defining runXX methods to implement overall functionality
def runNPCs():
	print("NPC time.")
	
def runBuildings():
	print("Building time.")
	
def runVillage():
	print("Village time.")
	
def runRumors():
	print("Rumor time.")
	
def runEncounters():
	print("Encounter time.")
	
def runLoot():
	print("Loot time.")
	
def runXPBudgetCalculator():
	print("XP Budget Calculating time.")
	
def runCombatEncounterGenerator():
	print("Combat Encounter Generating time.")
	
def runMonsterLists():
	print("Monster Lists time.")
	
def runWildMagicalEffects():
	print("Wild Magical Effects time.")
	
def runPrintAList():
	print("List printing time.")
	
def runSpellCards():
	print("Spell cards time.")

#starts app
app.go()
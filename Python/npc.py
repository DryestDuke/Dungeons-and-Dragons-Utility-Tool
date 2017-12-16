class npc():
	objString = ""
	def __init__(self, string="not set"):
		self.objString = string
	def display(self):
		print(self.objString)

#create a popup, with a button to generate an npc, where you can choose certain characteristics (race, etc)

#make sure to allow for 'saving'/'editing of saved' npcs

#saved npcs should be scrollable.. perhaps each is just a name that when you click on it a text-popup comes up with the npc's information (try to avoid too many windows, though)

#all npcs should be class object (so i can call myNpc.name, etc)

#make sure to incorporate new changes to NPCs in issues

#i am parsing files at beginning and passing the lists, once created, to the npcApp
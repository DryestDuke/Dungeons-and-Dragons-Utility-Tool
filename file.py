"""
a series of tools to enable me to be a good DM. immediately below are generic methods, created to make things easier for me.
below them are the actual specficially "d&d - related" methods
code written by a simple CS student, files (going along this program) written by said student & compiled from online sources (mostly compiled)
to use, simply call 'start()'
"""
from __future__ import print_function
import random


#runs through file with name given, returns list wherein each line of the file is an element of the list
def parseFile(filename):
	list = []
	fp = open(filename, "r")
	for line in fp:
		#all lines in all of my files end in newline, this gets in the way of clean printing
		newline = line.replace("\n", "")
		list.append(newline)
	fp.close()
	return list

#for given list, returns n random items from it
def randomLines(List, n=1):
	returnList = []
	for i in range(n):
		i = random.choice(List)
		returnList.append(i)
	return returnList
	
#for given list, print all items of it
def printList(L):
	for i in range(len(L)):
		print(L[i])
		
#calls for input from user in the form of an int. If it is not an int, prints error message and lets you try again :^)
def intInput(s=""):
	if s == "":
		input = raw_input()
	else:
		input = s
	try:
		input = int(input)
		return input
	except ValueError:
		#for all usage of intInput(), there is a chance they may have meant to roll a dice. so, see if input is parseable to a #d##
		#diceRoller(...) will print the result of the dice roll. however, we still haven't gotten an actual integer from the user...
		diceRoller(input, True)
		print("Retry: ", end="")
		return intInput()
		print("")
		
"""
General Outline of Program:
	default: dice roller
	: NPC
		:choose race?
	: Building
		:DMG/Trade Buildings
	: Village
	: Rumors/Plot Hooks
	: Random Encounter
	: Loot
		:Mundane, Trinkets, Magical
	: combat XP calculator
		:returns calculated XP for encounter of given size and level
	: combat encounter generator
		:enc. calc.
		:creatures, listed by type/environment/page in (which) book
	: Monster List
		:prints out monsters that fit a certain tag, or all mosnters sorted in a certain way
	: Random Magical Effect
		:for use in wild magic storms, wild magic in general, or for inspiration :^)
	: Spell Cards
		:enter list of spell names, seperated by commas. return spell cards with those names
	:exit
		:return out of 'main', resuming normal interpreter use
"""

#introduction to program, with some neat ASCII art, which then calls run() which calls itself ad-infinitum
def start(): #\t = 8 spaces #credit for ascii art below to: http://www.ascii-art.de/ascii/s/swords.txt
	toprint = """
     /:\ \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   (\""")
     |:| \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    III
     |:| \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    III
     |:| \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    III
     |:| \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  __III__
     |:| \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t/:-.___,-:\\
     |:| \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\]  |:|  [/
     |:| \t\t\t\t\t\tWelcome to the D&D Utility Tool!\t\t\t\t\t\t    |:|
     |:| \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    |:|
     |:| \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    |:|
 /]  |:|  [\ \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    |:|
 \:-'\"""`-:/ \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    |:|
   ""III""   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    |:|
     III     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    |:|
     III     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    |:|
     III     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    |:|
    (___)    \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    \:/"""
	print(" <----------------------------------------------------------------------------------------------------------------------------------------> ")
	print(toprint + "\n")
	print(" <----------------------------------------------------------------------------------------------------------------------------------------> ")
	run()
	
#main line of the program - takes in input (as option chosen) and then runs that option... simple!
def run():
	print("1: NPC, 2: Building, 3: Village, 4: Rumors/Plot Hooks, 5: Random Encounter, " 
	+ "6: Loot, 7: XP Budget Calculator, 8: Combat Encounter Generator, "
	+ "9: Monster Lists, 10: Wild Magical Effect, 11: Print a List, 12: Spell Cards, "
	+ "13: Exit\nChoice: ", end="")
	input = intInput()
	if(input == 1):
		npc(False, False)
	elif(input == 2):
		building()
	elif(input == 3):
		village()
	elif(input == 4):
		plotHooks()
	elif(input == 5):
		randomEncounter()
	elif(input == 6):
		loot()
	elif(input == 7):
		intro()
		xpBudgetCalculator()
		outro()
	elif(input == 8):
		combatEncounterGenerator()
	elif(input == 9):
		monsterLists()
	elif(input == 10):
		wildMagicalEffect()
	elif(input == 11):
		listPrinter()
	elif(input == 12):
		spellCards()
	elif(input == 13):
		return end()
	else:
		print("Invalid choice.")
		
	run()
	
#takes input, if in the form of #x(char)#y, rolls x y-sided di(c)e
#returns true if input is actually in the form of ^, and in such a case, it also prints the results of the die rolls [unsorted & sorted]
def diceRoller(input, printStuff=False):
	#must be at least 3 characters in input [1d1], otherwise... how would this even work
	if(len(input) < 3):
		return False
	#confirm input to be in proper form. do this by creating boolean...
	foundIt = False
	index = -1 #(this is for recording the location of the "splitting character" (see a few lines below for explanation of what that is))
	#...and walking through input, checking every character to be a digit. once non digit found, foundIt = True.
	#continue to walk through input, making sure every character after that is a digit. if another non-digit is found, error! return False
	for i in range(len(input)):
		if not(input[i].isdigit()) and foundIt == True:
			return False
		elif not(input[i].isdigit()) and foundIt == False: #'and foundIt == False' is redundant, it is implied by the flow of the logic
			foundIt = True
			index = i
		else: #this else is completely unnecessary. i like it though, so it stays
			continue
	
	#now, split string into two strings: from 0..index and index..len(input)
	#assign values to appropriate variables, make the rolls and include sum as well
	number_dice, number_sides = input.split(input[index])
	number_dice = int(number_dice)
	number_sides = int(number_sides) #normally, for these ^, I'd do a try/catch (except)... but I've already made sure they are ints!
	
	rolls = []
	sum = 0
	for i in range(number_dice):
		temp = random.randint(1,number_sides)
		rolls.append(temp)
		sum += temp
	
	if(printStuff):
		if(number_dice == 1):
			print(rolls,": ",sum)
		else:
			print("Unsorted:", rolls)
			print("Sorted:", sorted(rolls), ":", sum)
	return sum

#generate & print an NPC
def npc(chooseRace=False, withoutItems=False):
	race, name, age, sex, stats, moral, worth, trait, ideal, skill, trade, armor, arms, items = "","","","","","","","","","","","","",""
	
	races_list = parseFile("Races_List.txt")
	#firstly, deal with race
	if(chooseRace):
		print("Enter your race: ",end="")
		race = raw_input()
		if(race not in races_list):
			npc(True)#recurse, don't let them out until they enter an existing race.
	else:
		races_weighted_list = parseFile("Races_List - Weighted.txt")
		race = random.choice(races_weighted_list)
		
	#secondly, grab a name
	#there are some special cases for certain races: otherwise, use generic names
	if("Dragonborn" in race):
		forename_list = parseFile("Names_List - Dragonborn.txt")
		surname_list = parseFile("Names_List - Dragonborn, Surnames.txt")
		name = random.choice(forename_list) + " " + random.choice(surname_list)
	elif("Dwarf" in race):
		forename_list = parseFile("Names_List - Dwarf.txt")
		surname_list = parseFile("Names_List - Dwarf, Surnames.txt")
		name = random.choice(forename_list) + " " + random.choice(surname_list)
	elif("Half-Elf" in race):
		forename_list = parseFile("Names_List - Elf.txt")
		surname_list = parseFile("Names_List - Generic.txt")
		name = random.choice(forename_list) + " " + random.choice(surname_list)
	elif("Elf" in race):
		forename_list = parseFile("Names_List - Elf.txt")
		surname_list = parseFile("Names_List - Elf, Surnames.txt")
		name = random.choice(forename_list) + " " + random.choice(surname_list)
	elif("Orc" in race):
		#single name, either orcish or generic
		forename_list = parseFile("Names_List - Half-Orc, Surnames.txt")
		surname_list = parseFile("Names_List - Generic.txt")
		if(random.randint(1,2) == 1):
			name = random.choice(forename_list)
		else:
			name = random.choice(surname_list)
	elif("Tiefling" in race):
		#single name
		forename_list = parseFile("Names_List - Tiefling.txt")
		name = random.choice(forename_list)	
	else:
		forename_list = parseFile("Names_List - Generic.txt")
		name = random.choice(forename_list) + " " + random.choice(forename_list)
		
	#now that we have race & name, we might need to append certain information to the name depending on the race:
	race = raceSpecificNameAdditions(race)
	
	#thirdly, choose an age
	#the reason for multiple entries is to weight the list in favor of certain ages. you'll see this in quite a few places...
	ages_list = ["Child", "Teenager", "Young Adult", "Young Adult", "Adult", 
		"Adult", "Adult", "Adult", "Middle-Aged", "Middle-Aged", "Old", "Senior"]
	age = random.choice(ages_list)
	
	#fourthly, choose a sex
	if(random.randint(1,2) == 1):
		sex = "Male"
	else:
		sex = "Female"
		
	#stats, choose anywhere from 1 to 3 AS's, with scores of anywhere from -3 to +3
	abilityscores = ["Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma", "Beauty"]
	count = random.randint(1,3)
	if(count == 3):
		count = random.randint(1,3)
	templist = []
	for i in range(count):
		temp = random.randint(0,6)
		while temp in templist:
			temp = random.randint(0,6)
		templist.append(temp)
	templist = sorted(templist)
	#templist = sorted list of indexes of ability scores to be modified
	#now to add the -3 to +3 - going to be weighted to be most likely -1/+1
	for i in range(len(templist)):
		stats += abilityscores[templist[i]] + " " + AS_Permutation() + " | "
	
	#moral
	moral_list = ["Lawful", "Lawful", "Lawful", "Lawful", "Lawful", "Lawful", "Lawful", "Neutral", "Neutral", "Neutral", "Neutral", "Chaotic"]
	moral = random.choice(moral_list)
	moral_list = ["Good", "Good", "Good", "Neutral", "Neutral", "Neutral", "Neutral", "Neutral", "Neutral", "Neutral", "Neutral", "Evil"]
	moral += " " + random.choice(moral_list)
	if(moral == "Neutral Neutral"): #an unfortunate side effect of the way the alignment is computed, this is easily remedied as so :^)
		moral = "True Neutral"
	
	#worth
	worth_list = parseFile("Worth_List - Weighted.txt") #"worth-list" is one hell of a WORTH-LESS list! lol
	worth = random.choice(worth_list)
	
	#trait
	if("Good" in moral):
		trait_list = parseFile("Traits_List - Good.txt")
	elif("Neutral" in moral):
		trait_list = parseFile("Traits_List - Neutral.txt")
	elif("Evil" in moral):
		trait_list = parseFile("Traits_List - Evil.txt")
	trait = random.choice(trait_list)
	
	#ideal
	ideal_list = parseFile("Ideals_List.txt")
	ideal = random.choice(ideal_list)
	
	#skill - all skills in their own lists, master list of all skills for which the NPC has a postive (i.e.; "+")
	strSkills = parseFile("Skills_List - STR.txt")
	dexSkills = parseFile("Skills_List - DEX.txt")
	conSkills = parseFile("Skills_List - CON.txt")
	intSkills = parseFile("Skills_List - INT.txt")
	wisSkills = parseFile("Skills_List - WIS.txt")
	chaSkills = parseFile("Skills_List - CHA.txt")
	beaSkills = parseFile("Skills_List - BEA.txt")
	
	masterList = []
	if("Strength +" in stats):
		masterList.append(strSkills)
	if("Dexterity +" in stats):
		masterList.append(dexSkills)
	if("Constitution +" in stats):
		masterList.append(conSkills)
	if("Intelligence +" in stats):
		masterList.append(intSkills)
	if("Wisdom +" in stats):
		masterList.append(wisSkills)
	if("Charisma +" in stats):
		masterList.append(chaSkills)
	if("Beauty +" in stats):
		masterList.append(beaSkills)
	if("+" not in stats):
		skill = "None" # :^(
	else:
		#now choose random skillList from masterList, and random skill from that random skillList
		tempList = random.choice(masterList)
		skill = random.choice(tempList)
		
	#trade. this one is *simple*
	trade = getTrade()
	
	#armor
	armor_list = parseFile("Armor_List.txt")
	armor = random.choice(armor_list)
	
	#arms
	arms_list = parseFile("Weapon_List.txt")
	arms = random.choice(arms_list)
	
	#items
	items = getItems()

	
	toPrint = """\
-------------------------------
 ><                         >< 
Name: """ + name + """
   Race: """ + race + """
	Age: """ + age + """
	Sex: """ + sex + """
	 Stats: """ + stats + """
	 Moral: """ + moral + """
	 Worth: """ + worth + """
	 Trait: """ + trait + """
	 Ideal: """ + ideal + """
	 Skill: """ + skill + """
	 Trade: """ + trade + """
"""
	if not (withoutItems):
		toPrint += """\

Armor: """ + armor + """
Arms: """ + arms + """
Items: \n""" + items + """
 ><                         >< 
-------------------------------
	"""
	if(withoutItems):
		toPrint += """\
><                         >< 
------------------------------- """
	
	print(toPrint)
	
#helper function for ^. functionality should be obvious from reading method
def raceSpecificNameAdditions(race):
	if "High-Elf" in race: #not just 'if race == "High-Elf":' b/c of half-elf
		#race += , with skin/eyes/hair of _color_ (choose one of them)
		templist = parseFile("Colors_List.txt")
		templist_two = ["Skin", "Eyes", "Hair"]
		race += " with " + random.choice(templist_two) + " of " + random.choice(templist)
	elif race == "Wood-Elf":
		#race += , devoted to a _item_
		race += " devoted to a/an " + random.choice(parseFile("Words_List - Nouns.txt"))
	elif race == "Forest Gnome":
		#race += possibility just a normal forest gnome, or might be a leprechaun lol
		templist = ["with a tall hat and a penchant for rhymes", "with peachy skin and a big earth-colored beard", "with orange hair and green-tinted skin", "with fiery red hair and a brilliant green beard!"]
		temp = ", " + random.choice(templist)
		if random.randint(1,2) == 1:
			race += temp
	elif race == "Half-Orc":
		#race += with paint in the shape of a _thing_
		if random.randint(1,4) == 1:
			race += ", with " + random.choice(parseFile("Colors_List.txt")) + " paint in the shape of a/an " + random.choice(parseFile("Words_List - Nouns.txt"))
	elif race == "Changeling":
		#in the shape of a _race_ (not race, but new_race)
		temp = random.choice(parseFile("Races_List - Weighted.txt"))
		while temp == "Changeling":
			temp = random.choice(parseFile("Races_List - Weighted.txt"))
		race += ", in the form of a/an " + temp
	return race
	
#helper function for npc(...)
def AS_Permutation():
	list = ["-3", "-2", "-2", "-1", "-1", "-1", "+1", "+1", "+1", "+1", "+2", "+2", "+3"]
	return random.choice(list)

#helper function (used in both building(...) & npc(...)
def getTrade():
	trade_list = parseFile("Jobs_List.txt")
	return random.choice(trade_list)
	
def getItems(n=0):
	items = ""
	belongings_list = parseFile("Belongings_List.txt")
	minorMagicalItems_list = parseFile("Items_List.txt")
	trinkets_list = parseFile("Trinkets_List.txt")
	#generate n (default 4, for npc's)	mundane belongings
	if(n==0):
		n=4
	for i in range(n):
		items += "\t> " + random.choice(belongings_list) + "\n"
	#for the fifth item on the NPC, roll for 1: mundane, 2: minor magical, 3: trinket
	temp = random.randint(1,3)
	if(temp == 1):
		items += "\t> " + random.choice(belongings_list)
	elif(temp == 2):
		items += "\t> " + random.choice(minorMagicalItems_list)
	else:
		items += "\t> " + random.choice(trinkets_list)
	return items
	
#pick & print a series of buildings, from two templates: DMG, Trade Buildings
def building(n=0):
	intro()
	
	toPrint = ""
	if(n==0):
		print("Amount: ",end="")
		n = intInput()
	#n = amount of buildings to produce
	for i in range(n):
		choice = random.randint(1,3)
		if(choice == 1):
			toPrint += "\n\t> " + Trade_Building()
		else:
			toPrint += "\n\t> " + DMG_Building()
	print(toPrint)
	outro()
	
#helper function for building(...)	
def DMG_Building():
	toReturn = ""
	
	generalDMG = parseFile("Buildings_List - General.txt")
	namedDMG = parseFile("Buildings_List - Named The.txt")
	namesFirst = parseFile("Buildings_List - Names, First Part.txt")
	namesSecond = parseFile("Buildings_List - Names, Second Part.txt")
	
	choice = random.randint(1,4)
	if(choice == 1):
		toReturn = random.choice(generalDMG)
	elif(choice == 2): #"A <namedDMG>, named the <namesSecond> and the <namesSecond>"
		#to do this, have to drop the period on the first namesSecond
		toReturn += random.choice(namedDMG)
		temp = random.choice(namesSecond)
		toReturn += temp[:len(temp)-1] + " "
		toReturn += random.choice(namesSecond)
		
	else: #"A <namedDMG>, named the <namesFirst> <namesSecond>"
		#need to drop first four characters of 'namesFirst' b/c of a mistake I made :^)
		toReturn += random.choice(namedDMG)
		temp = random.choice(namesFirst)
		temp = temp[4:]
		toReturn += temp
		toReturn += random.choice(namesSecond)
		
	
	return toReturn
	
#helper function for building(...)
def Trade_Building():
	return "Bulding of/for " + getTrade()
	
#generate & print a village
def village():
	intro()
	
	output = "~~~ the " + villageSize() + " of " + villageName() + " ~~~\n"
	
	temp = random.randint(1,3)
	if(temp == 1):
		output += "  Composed of a single race: \n   "
	else:
		output += "  Composed of a number of races: \n   "
	tempList = randomLines(parseFile("Races_List.txt"), n=temp)
	for i in range(temp):
		output += tempList[i] + " | "
	output = output[:len(output)-3] + "\n"
	
	if(temp != 1):
		output += "\t> " + raceRelations() + "\n"
	#output += "\t> " + currency() + "\n"
	output += "\t> " + tradeGoods() + "\n"
	output += "\t> Leader is: " + rulerPerception() + "\n"
	output += "  -----------\n"
	output += "    A notable trait of the village...\n\t> " + notableTrait()
	output += "\n    It is known for it's...\n\t> " + knownFor()
	output += "\n    It is currently in the midst of a calamity...\n\t> " + calamity()
	
	print(output)
	outro()

	
def villageSize():
	list = ["Isolated Dwelling", "Hamlet", "Village", "Village", "Town", "Town", "Town", "Town", "Large Town", "Large Town", "City", "Large City"]
	return random.choice(list)
	
def villageName():
	list = parseFile("Villages_List - Names.txt")
	return random.choice(list)
	
def raceRelations():
	list = parseFile("Villages_List - Race Relations.txt")
	return random.choice(list)
	
def currency():
	list = ["PLACEHOLDER - WAITING ON MORE LORE"]
	return random.choice(list)
	
def tradeGoods():
	return "Trade Goods: " + random.choice(parseFile("Trade Goods_List.txt"))
	
def rulerPerception():
	list = parseFile("Villages_List - Ruler Perception.txt")
	return random.choice(list)
	
def notableTrait():
	list = parseFile("Villages_List - Village Trait.txt")
	return random.choice(list)
	
def knownFor():
	list = parseFile("Villages_List - Known For.txt")
	return random.choice(list)
	
def calamity():
	list = parseFile("Villages_List - Current Calamity.txt")
	return random.choice(list)
		
	
#pick & print  a number of plot hooks
def plotHooks(n=0):
	intro()

	toPrint = ""
	if(n==0):
		print("Amount: ",end="")
		n = intInput()
	#n = amount of plot hooks to produce
	list = parseFile("Rumor_List.txt")
	for i in range(n):
		toPrint += "\n\t> " + random.choice(list)
	
	print(toPrint)
	outro()
	

#pick & print a random encounter
def randomEncounter():
	intro()
	
	print("1: On the Road | 2: By the Sea | 3: In the Mountains | 4: Through the City\nYour Choice: ", end="")
	choice = intInput()
	if(choice == 1):
		filename = "Encounters_List - On the Road.txt"
	elif(choice == 2):
		filename = "Encounters_List - By the Sea.txt"
	elif(choice == 3):
		filename = "Encounters_List - In the Mountains.txt"
	elif(choice == 4):
		filename = "Encounters_List - Through the City.txt"
	else:
		return randomEncounter()
	list = parseFile(filename)
	
	print("\t> " + random.choice(list))
	outro()
 
 	
#amount of mundane, trinkets, magic items
def loot(choice=0, amount=0):
	intro()
	
	output = ""
	mundane_list = parseFile("Belongings_List.txt")
	trinket_list = parseFile("Trinkets_List.txt")
	magical_list = parseFile("Items_List.txt")
	if choice==0:
		print("1: Mundane | 2: Trinket | 3: Magical | 4: Combination\nChoice: ", end="")
		input = intInput()
	else:
		input = choice
	if amount==0:
		print("Amount: ", end="")
		amount  = intInput()
	if input==1:
		for i in range(amount):
			output += "\t> " + random.choice(mundane_list) + "\n"
	elif input==2:
		for i in range(amount):
			output += "\t> " + random.choice(trinket_list) + "\n"
	elif input==3:
		for i in range(amount):
			output += "\t> " + random.choice(magical_list) + "\n"
	elif input==4:
		#this is the only 'complex' one...
		#loop amount # of times:
			#for each pass through the loop, roll between mundane <-> trinket <-> magical
			#add to respective counters
			#after loop has ended, assemble list in order (could just add each one to output after rolling for them, but I want output...
				#...to be ordered mundane -> trinket -> magical)
		mundane_ctr = 0
		trinket_ctr = 0
		magical_ctr = 0
		templist = [1, 1, 1, 1, 1, 1, 1, 2, 2, 3] #1=mundane, 2=trinket, 3=magical
		for i in range(amount):
			temp = random.choice(templist)
			if(temp==1):
				mundane_ctr += 1
			elif(temp==2):
				trinket_ctr += 1
			else:
				magical_ctr += 1
		for i in range(mundane_ctr):
			output += "\t> " + random.choice(mundane_list) + "\n"
		for i in range(trinket_ctr):
			output += "\t> " + random.choice(trinket_list) + "\n"
		for i in range(magical_ctr):
			output += "\t> " + random.choice(magical_list) + "\n"
	else:
		return loot()
	
	#to get rid of the last newline. there might be a better way of doing this, but I've been up for 27 hours straight
	output = output[:len(output)-1]
	
	print(output)
	outro()

	
#uses enc. calc., and the repository of creatures indexed by...
""" 
	Creatures_List - Arctic.txt
	Creatures_List - Coastal.txt
	Creatures_List - Desert.txt
	Creatures_List - Forest.txt
	Creatures_List - Grassland.txt
	Creatures_List - Hill.txt
	Creatures_List - Mountain.txt
	Creatures_List - Swamp.txt
	Creatures_List - Underdark.txt
	Creatures_List - Underwater.txt
	Creatures_List - Urban.txt
"""
#breakdown: ask environment, n = # players, m = average level of players
	#load monsters from given environment into list
	#calculate XP budget based off n & m
def combatEncounterGenerator():
	intro()
	print("1: Arctic, 2: Coastal, 3: Desert, 4: Forest, 5: Grassland, 6: Hill, 7: Mountain, 8: Swamp, 9: Underdark, 10: Underwater, 11: Urban, 12: Anywhere")
	print("Environment: ", end="")
	choice = intInput()
	ml = []#ml = monster_list
	if choice == 1:
		ml = parseFile("Creatures_List - Arctic.txt")
	elif choice == 2:
		ml = parseFile("Creatures_List - Coastal.txt")
	elif choice == 3:
		ml = parseFile("Creatures_List - Desert.txt")
	elif choice == 4:
		ml = parseFile("Creatures_List - Forest.txt")
	elif choice == 5:
		ml = parseFile("Creatures_List - Grassland.txt")
	elif choice == 6:
		ml = parseFile("Creatures_List - Hill.txt")
	elif choice == 7:
		ml = parseFile("Creatures_List - Mountain.txt")
	elif choice == 8:
		ml = parseFile("Creatures_List - Swamp.txt")
	elif choice == 9:
		ml = parseFile("Creatures_List - Underdark.txt")
	elif choice == 10:
		ml = parseFile("Creatures_List - Underwater.txt")
	elif choice == 11:
		ml = parseFile("Creatures_List - Urban.txt")
	elif choice == 12:
		ml = []
		ml += parseFile("Creatures_List - Arctic.txt")
		ml += parseFile("Creatures_List - Coastal.txt")
		ml += parseFile("Creatures_List - Desert.txt")
		ml += parseFile("Creatures_List - Forest.txt")
		ml += parseFile("Creatures_List - Grassland.txt")
		ml += parseFile("Creatures_List - Hill.txt")
		ml += parseFile("Creatures_List - Mountain.txt")
		ml += parseFile("Creatures_List - Swamp.txt")
		ml += parseFile("Creatures_List - Underdark.txt")
		ml += parseFile("Creatures_List - Underwater.txt")
		ml += parseFile("Creatures_List - Urban.txt")
	else:
		return combatEncounterGenerator()
			
	print("1: Easy, 2: Medium, 3: Hard, 4: Deadly")
	print("Difficulty: ", end="")
	d = intInput()
	while not (d == 1 or d == 2 or d == 3 or d == 4):
		print("1: Easy, 2: Medium, 3: Hard, 4: Deadly")
		print("Difficulty: ", end="")
		d = intInput()
			
	x = xpBudgetCalculator(d)
	
	#now, i've gotten my XP budget and my list of the appropriate type of monsters
	#create a new list (monster_list) that is composed of actual monster objects
	monster_list = []
	for line in ml:
		templist = line.split(",")
		monster_list.append(monster(templist[0], templist[1], templist[2], templist[3]))
		
	print(chooseCreatures_intelligentMethod(monster_list, x))
	
 	outro()
	
#calculates (and returns/prints) the XP budget for encounter of given size/level
def xpBudgetCalculator(d=-1, n=-1, m=-1):
	if(n==-1):
		print("Number of Players: ", end="")
		n = intInput()
	if(m==-1):
		print("Average Level: ", end="")
		m = intInput()
	#d will be -1 only when I am printing all difficulty xp values.
	if(d==-1):
		print("Easy:", xpBudgetCalculator(1,n,m), end=" | ")
		print("Medium:", xpBudgetCalculator(2,n,m), end=" | ")
		print("Hard:", xpBudgetCalculator(3,n,m), end=" | ")
		print("Deadly:", xpBudgetCalculator(4,n,m))
	#x = xpbudget
	x = -1
	#t = temporary value, used for calculating ^
	t = -1
	if m == 1:
		if d == 1:
			t = 25
		elif d == 2:
			t = 50
		elif d == 3:
			t = 75
		else:
			t = 100
	elif m == 2:
		if d == 1:
			t = 50
		elif d == 2:
			t = 100
		elif d == 3:
			t = 150
		else:
			t = 200
	elif m == 3:
		if d == 1:
			t = 75
		elif d == 2:
			t = 150
		elif d == 3:
			t = 225
		else:
			t = 400
	elif m == 4:
		if d == 1:
			t = 125
		elif d == 2:
			t = 250
		elif d == 3:
			t = 375
		else:
			t = 500
	elif m == 5:
		if d == 1:
			t = 250
		elif d == 2:
			t = 500
		elif d == 3:
			t = 750
		else:
			t = 1100
	elif m == 6:
		if d == 1:
			t = 300
		elif d == 2:
			t = 600
		elif d == 3:
			t = 900
		else:
			t = 1400
	elif m == 7:
		if d == 1:
			t = 350
		elif d == 2:
			t = 750
		elif d == 3:
			t = 1100
		else:
			t = 1700
	elif m == 8:
		if d == 1:
			t = 450
		elif d == 2:
			t = 900
		elif d == 3:
			t = 1400
		else:
			t = 2100
	elif m == 9:
		if d == 1:
			t = 550
		elif d == 2:
			t = 1100
		elif d == 3:
			t = 1600
		else:
			t = 2400
	elif m == 10:
		if d == 1:
			t = 600
		elif d == 2:
			t = 1200
		elif d == 3:
			t = 1900
		else:
			t = 2800
	elif m == 11:
		if d == 1:
			t = 800
		elif d == 2:
			t = 1600
		elif d == 3:
			t = 2400
		else:
			t = 3600
	elif m == 12:
		if d == 1:
			t = 1000
		elif d == 2:
			t = 2000
		elif d == 3:
			t = 3000
		else:
			t = 4500
	elif m == 13:
		if d == 1:
			t = 1100
		elif d == 2:
			t = 2200
		elif d == 3:
			t = 3400
		else:
			t = 5100
	elif m == 14:
		if d == 1:
			t = 1250
		elif d == 2:
			t = 2500
		elif d == 3:
			t = 3800
		else:
			t = 5700
	elif m == 15:
		if d == 1:
			t = 1400
		elif d == 2:
			t = 2800
		elif d == 3:
			t = 4300
		else:
			t = 6400
	elif m == 16:
		if d == 1:
			t = 1600
		elif d == 2:
			t = 3200
		elif d == 3:
			t = 4800
		else:
			t = 7200
	elif m == 17:
		if d == 1:
			t = 2000
		elif d == 2:
			t = 3900
		elif d == 3:
			t = 5900
		else:
			t = 8800
	elif m == 18:
		if d == 1:
			t = 2100
		elif d == 2:
			t = 4200
		elif d == 3:
			t = 6300
		else:
			t = 9500
	elif m == 19:
		if d == 1:
			t = 2400
		elif d == 2:
			t = 4900
		elif d == 3:
			t = 7300
		else:
			t = 10900
	elif m == 20:
		if d == 1:
			t = 2800
		elif d == 2:
			t = 5700
		elif d == 3:
			t = 8500
		else:
			t = 12700
	else:
		return xpBudgetCalculator()
	return n*t
	
#randomly choose a creature from the list, and if the XP of the creature is less than X, print it and subtract its XP from X
#___THIS___IS___A___TERRIBLE___ALGORITHM
def chooseCreatures_randomMethod(monster_list, xpbudget):
	output = ""
	while xpbudget > 0:
		temp = random.choice(monster_list)
		xpbudget -= temp.xp
		output += temp.__str__() + "\n"
	return output

""" Breakdown of functionality. And this needs to be a multiple line comment b/c this is not necessarily intuitive :^)
	*Take in monster_list, xpbudget
	*while xpbudget > 0:
		*choose monster from list, add it to output, subtract it's XP from xpbudget
			*three types of monsters (classified by XP):
				>...
				>small = 1/8 * xpbudget
				>medium = 1/4 * xpbudget
				>large = 1/2 * xpbudget
				>...
			*choose one of these types monsters (medium most likely), and find the creature in the list with XP closest to the monster type
			*add it to ouput, subtract it's xp from xpbudget, and then roll 1d3 -> on a 1, del monster
	*return output
"""	
#usage note: will commonly build encounter just a bit over the actual recommended XP level. just.... fyi
def chooseCreatures_intelligentMethod(monster_list, xpbudget):
	output = ""
	xpbudget = int(str(xpbudget))
	print("XP Budget:", xpbudget, "\n")
	#need to sort monster_list by XP of monsters inside
	sml = sorted(monster_list, key=lambda x: x.xp)#god i love python
	types_of_monsters = [xpbudget/8.0, xpbudget/8.0, xpbudget/4.0, xpbudget/4.0, xpbudget/4.0, xpbudget/4.0, xpbudget/2.0, xpbudget/2.0, xpbudget]
	while xpbudget > 0:
		#grab a single monster
		expectedXP = random.choice(types_of_monsters)
		#go through sml & find the last monster.xp <= expectedXP
		for i in range(len(sml)):
			if sml[i].xp > expectedXP:
				if(i == 0):
					output += sml[i].__str__() + "\n"
					xpbudget -= sml[i].xp
					temp = random.randint(1,2)
					if(temp == 1):
						del sml[i]
					break
				elif(i == len(sml)-1):
				#the flow of this algorithm will NEVER let the program choose the last monster (unless I've deleted every other monster)
					#this prevents that
					templist = [sml[i], sml[i-1]]
					temp = random.choice(templist)
					output += temp.__str__() + "\n"
					xpbudget -= temp.xp
					tempint = random.randint(1,2)
					if(tempint == 1):
						del temp
					break
				else:
				#the reason for this is the sml[i] (in this case) will always be greater than the xpbudget, I want to default to >= xpbudget
					#in order to enforce more variety, add random value to i
					temphold = i
					i += random.randint(-6,6)
					while((i < 0)or(i > len(sml)-1)):
						i = temphold
						i += random.randint(-6,6)
					output += sml[i-1].__str__() + "\n"
					xpbudget -= sml[i-1].xp
					temp = random.randint(1,3)
					if(temp == 1):
						del sml[i-1]
					break
	return output
	
#prints monsters by order given via input, or just in any way
def monsterLists():
	intro()
	
	#order monsters by NAME, TYPE, XP, ENVIRONMENT, BOOK/PG
	print("1: Arctic, 2: Coastal, 3: Desert, 4: Forest, 5: Grassland, 6: Hill, 7: Mountain, 8: Swamp, 9: Underdark, 10: Underwater, 11: Urban, 12: Anywhere")
	print("Environment: ", end="")
	choice = intInput()
	ml = []#ml = monster_list
	if choice == 1:
		ml = parseFile("Creatures_List - Arctic.txt")
	elif choice == 2:
		ml = parseFile("Creatures_List - Coastal.txt")
	elif choice == 3:
		ml = parseFile("Creatures_List - Desert.txt")
	elif choice == 4:
		ml = parseFile("Creatures_List - Forest.txt")
	elif choice == 5:
		ml = parseFile("Creatures_List - Grassland.txt")
	elif choice == 6:
		ml = parseFile("Creatures_List - Hill.txt")
	elif choice == 7:
		ml = parseFile("Creatures_List - Mountain.txt")
	elif choice == 8:
		ml = parseFile("Creatures_List - Swamp.txt")
	elif choice == 9:
		ml = parseFile("Creatures_List - Underdark.txt")
	elif choice == 10:
		ml = parseFile("Creatures_List - Underwater.txt")
	elif choice == 11:
		ml = parseFile("Creatures_List - Urban.txt")
	elif choice == 12:
		ml = []
		ml += parseFile("Creatures_List - Arctic.txt")
		ml += parseFile("Creatures_List - Coastal.txt")
		ml += parseFile("Creatures_List - Desert.txt")
		ml += parseFile("Creatures_List - Forest.txt")
		ml += parseFile("Creatures_List - Grassland.txt")
		ml += parseFile("Creatures_List - Hill.txt")
		ml += parseFile("Creatures_List - Mountain.txt")
		ml += parseFile("Creatures_List - Swamp.txt")
		ml += parseFile("Creatures_List - Underdark.txt")
		ml += parseFile("Creatures_List - Underwater.txt")
		ml += parseFile("Creatures_List - Urban.txt")
		"""
		NEED TO FORMAT A NEW LIST FOR ALL THE ONES FROM DONJON THAT WEREN'T PUT INTO ANY LIST IN PARTICULAR - READ: DEVA, etc
		"""
		ml += parseFile("Creatures_List - Monster Manual, Forgotten.txt")
	else:
		return monsterLists()
	
	#remove duplicate entries. mostly for choice == 12...
	temp = []
	for m in ml:
		if m not in temp:
			temp.append(m)
	ml = temp
	
	#ml is now of the monsters of a certain type they wish
	monster_list = []
	for line in ml:
		templist = line.split(",")
		monster_list.append(monster(templist[0], templist[1], templist[2], templist[3]))
		
	#monster_list is now a list of actual monster objects, useful for sorting
	sortMonsterList(monster_list, ml)
	
	outro()
	
#gives options to sort a list of monsters (objects), taking input: list of monsters, raw list of monster strings
def sortMonsterList(monster_list, ml):
	print("\nDo you wish to sort by...\n\n1: Name, 2: Type, 3: XP, 4: Book/Pg, 5: Keyword")
	choice = -1
	while choice < 0 or choice > 5:
		print("Choice: ", end="")
		choice = intInput()
	sml = []#sorted_monster_list
	if choice == 1:
		sml = sorted(monster_list, key=lambda x: x.name)
	if choice == 2:
		sml = sorted(monster_list, key=lambda x: x.type)
	if choice == 3:
		sml = sorted(monster_list, key=lambda x: x.xp)
	if choice == 4:
		sml = sorted(monster_list, key=lambda x: x.entry)
	if choice == 5:
		#now we do not use sml. instead, we go through the list of strings (ml), and... you can probably guess
		print("Keyword: ", end="")
		keyword = raw_input()
		foundOne = False
		for line in ml:
			if keyword in line:
				foundOne = True
				sml.append(line)
		if not foundOne:
			print("Sorry, keyword does not exist for given creatures.")
			return
		printList(sml)
		print("\nWould you like to continue sorting this new list?\n1: Yes, 2: No\nChoice: ", end="")
		yes_or_no = intInput()
		if(yes_or_no == 1):
			sortMonsterList(makeMonsterList(sml), sml)
		return
	print("")
	printList(sml)
	
#takes in a list of strings containing the relevant info formatted correctly, returns list of monster objects
def makeMonsterList(ml):
	monster_list = []
	for line in ml:
		templist = line.split(",")
		monster_list.append(monster(templist[0], templist[1], templist[2], templist[3]))
	return monster_list
	
#returns N amount of random magical effects
def wildMagicalEffect(n=0):
	intro()
	if n == 0:
		print("Amount: ", end="")
		n = intInput()
	list = parseFile("Net Libram of Random Magical Effects, V2.txt")
	new_list = randomLines(list, n)
	for i in new_list:
		print("\t> " + i)
	outro()
	
#gives option to print lists that might be useful to have displayed during players
def listPrinter():
	intro()
	print("1: Races, 2: Races (Descriptive), " +
	"3: Worth List, 4: Worth List (Weighted)")
	choice = -1
	while(choice < 1 or choice > 4):
		print("Choice: ", end="")
		choice = intInput()
	
	filename = ""
	if choice == 1:
		filename = "Races_List.txt"
	elif choice == 2:
		filename = "Races_List - Descriptions.txt"
	elif choice == 3:
		filename = "Worth_List.txt"
	elif choice == 4:
		filename = "Worth_List - Weighted.txt"
		
	print("")
	printList(parseFile(filename))	
	print("")
		
	outro()
	
#for list of spell names, seperated by commas, returns spell cards fitting those names
def spellCards(names="", doSpellList = False):
	intro()
	if doSpellList:
		return spellList()
	if names == "":
		print("Would you like to (1) Enter Spell Names, or (2) Search Spell List: ", end="")
		choice = intInput()
		if(choice == 2):
			return spellList()
		print("Names: ", end="")
		names = raw_input()
	nl = names.split(", ")#nl = names_list
	list_of_spells = parseFile("Spells_List.txt")
	list_for_formatting = []
	for item in list_of_spells:
		temp = item.split(",")
		if temp[0] in nl:
			list_for_formatting.append(item)
	sl = []#spell list
	for spell in list_for_formatting:
		sl.append(createSpellCard(spell))
	for name in nl:
		for spellcard in sl:
			if spellcard.name == name:
				print(spellcard)
	outro()
	
#alternate usage of spellCards allows for one to walk through all spells, sorting/searching etc...
def spellList():
	temp = parseFile("Spells_List.txt")
	sl = []#spell list
	for line in temp:
		list = line.split(", ")
		description = ""
		for i in range(len(list)):
			if i >= 6:
				description += list[i] + ", "
		sl.append(spell(list[0], list[1], list[2], list[3], list[4], list[5], description))
	#now give the option to sort by keyword in one of the categories (and once keyword is entered, also sort alphabetically through that category, then repeat | usage: give me all divination spells, touch range, casting time 1 action)
	choice = 0
	while choice == 0:
		print("Would you like to sort your list by (1) a category or (2) keyword: ", end="")
		cat_or_key = intInput()
		if cat_or_key == 1:
			print("By... 1: Name, 2: Type, 3: Casting Time, 4: Range, 5: Components, 6: Duration, 7: Description", end="")
			organizeby = intInput()
			if organizeby == 1:
				sl = sorted(sl, key=lambda x: x.name)
			elif organizeby == 2:
				sl = sorted(sl, key=lambda x: x.type)
			elif organizeby == 3:
				sl = sorted(sl, key=lambda x: x.casttime)
			elif organizeby == 4:
				sl = sorted(sl, key=lambda x: x.range)
			elif organizeby == 5:
				sl = sorted(sl, key=lambda x: x.comp)
			elif organizeby == 6:
				sl = sorted(sl, key=lambda x: x.dur)
			elif organizeby == 7:
				sl = sorted(sl, key=lambda x: x.description)
			else:
				print("Invalid value!")
				continue
		else:
			print("Which field, 1: Name, 2: Type, 3: Casting Time, 4: Range, 5: Components, 6: Duration, 7: Description", end="")
			organizeby = intInput()
			print("Keyword: ", end="")
			keyword = raw_input()
			temp = []
			if organizeby == 1:
				for s in sl:
					if keyword in s.name:
						temp.append(s)
			elif organizeby == 2:
				for s in sl:
					if keyword in s.type:
						temp.append(s)
			elif organizeby == 3:
				for s in sl:
					if keyword in s.casttime:
						temp.append(s)
			elif organizeby == 4:
				for s in sl:
					if keyword in s.range:
						temp.append(s)
			elif organizeby == 5:
				for s in sl:
					if keyword in s.comp:
						temp.append(s)
			elif organizeby == 6:
				for s in sl:
					if keyword in s.dur:
						temp.append(s)
			elif organizeby == 7:
				for s in sl:
					if keyword in s.description:
						temp.append(s)
			else:
				print("Invalid value!")
				continue
			sl = temp
		printList(sl)	
		print("Enter 0 to Continue Sorting: ", end="")
		choice = intInput()
	outro()
		

#takes in a string containing a spell, but not formatted. it then formats it and returns that
def createSpellCard(spell_string=""):
	if spell_string == "":
		print("Use in conjunction with spellCards(...).")
		return "Improper Use"
	list = spell_string.split(", ")
	#list: [0]=name, [1]=level/school(Type), [2]=cast time, [3]=range, [4]=comp., [5]=dur., [6...]=description
	description = ""
	for i in range(len(list)):
		if i >= 6:
			description += list[i] + ", " #add the commas b/c the reason i have to reassemble this is b/c it got split along the commas earlier (like 5 lines above^^^^^)
	spellcard = spell(list[0], list[1], list[2], list[3], list[4], list[5], description)
	return spellcard
	
#simple method, but might one day want to add more to it. besides, it's probably good programming practice. idk, tho
def end():
	print("Thanks for dropping bye!")
	return None

#intro and outro for each section
def intro():
	print("""\
-------------------------------
 ><                         ><""")
	
def outro():
	print("""\
 ><                         >< 
-------------------------------""")
	
class spell():
	def __init__(self, name, type, casttime, range, comp, dur, description):
		self.name = name
		self.type = type
		self.casttime = casttime
		self.range = range
		self.comp = comp
		self.dur = dur
		self.description = description
		
	def __str__(self):
		spellcard = """\
		________________
		| """ + self.name + """
		| """ + self.type + """
		| """ + self.casttime + """
		| """ + self.range + """
		| """ + self.comp + """
		| """ + self.dur + """
		| """ + self.description + """
		"""
		return spellcard
	
class monster(): #should really be called "creatures", but it's legacy code by this point
	def __init__(self, name, type, xp, entry):
		self.name = name
		self.type = type
		self.xp = int(xp)
		self.entry = entry
		
	def __str__(self):
		return self.name + ", " + self.type + ", " + str(self.xp) + ", " + self.entry
	
#  Bottom of program deliberately left blank	
#  because otherwise
#  i am always typing at the bottom of the program
#  and that just feels absolutely disgusting to me
#  :^)

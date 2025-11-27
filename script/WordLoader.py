laCode = []
taCode = []

with open("C:/Users/Shourjo/Downloads/Wordle_Word_Dictionary/wordle-La.txt", "r") as laFile:
    c = 'a'
    laCode.append("\n\t\t{")
    for line in laFile:
        laCode.append("\"" + "".join(line.split()).upper() + "\"")
        laCode.append(", ")
    laCode.pop()
    laCode.append("}")
    laCode.append(", ")

with open("C:/Users/Shourjo/Downloads/Wordle_Word_Dictionary/wordle-Ta.txt", "r") as laFile:
    c = 'a'
    taCode.append("\n\t\t{")
    for line in laFile:
        taCode.append("\"" + "".join(line.split()).upper() + "\"")
        taCode.append(", ")
    taCode.pop()
    taCode.append("}")
    taCode.append(", ")

with open("src/Words.java", "w") as javaFile:
    taCode.pop()
    javaFile.write("public class Words {\n\tpublic String[][] wordsList = {")
    javaFile.write("".join(laCode))
    javaFile.write("".join(taCode))
    javaFile.write("\n\t};\n}")
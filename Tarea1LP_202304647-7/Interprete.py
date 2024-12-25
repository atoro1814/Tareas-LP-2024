import re
import sys

# Definición de patrones y variables globales
letras_mayus = r'[A-Z]'
letras_minus = r'[a-z]'
variable = rf'\$_{letras_mayus}({letras_mayus}|{letras_minus})*'
patron_variable = r'\$_[A-Z][A-Za-z]*'
patron_oper_un = r'ASIG'
patron_oper_bin = r'\+|\*|>|=='
patron_string = r'#\s*([A-Z]|[a-z]|[0-9]| )*\s*#'
patron_bool = r'True|False'
patron_entero = r'0|[1-9][0-9]*'
patron_operando = rf'{patron_variable}|{patron_bool}|{patron_string}|{patron_entero}'
patron_mostrar = rf'\s*MOSTRAR\s*\(({patron_variable})\)\s*'
patron_else = r'\s*\}\s*else\s*\{'
patron_llave = r'\s*\}\s*'
patron_if = r'\s*if\s*\((\$_[A-Z][A-Z|a-z]*)\)\s*\{'
patron_linea_vacia = r'\s*'

global bandera
bandera = True
global pila_if
pila_if = []
global memoria_variables
memoria_variables = {}
global linea
linea = 1
global contador_if
contador_if = 0
global contador_else
contador_else = 0
global contador_llave
contador_llave = 0
valores_mostrar = []


def detector_palabra_clave(string,linea):
    '''
    ***
    Parametro 1: string : str
    Parametro 1: linea : int
    ***
    Tipo de Retorno: None
    ***
    Verifica que cada linea este bien escrita sintacticamente y controla la variable bandera que permite la lectura de las lineas
    y gestiona la logica condidicional
    '''

    global bandera
    global contador_if
    
    

    if bandera:
        
        if string.startswith("DEFINE"):
            declaracion(string)
        elif string.startswith("DP"):
            procesar(string)
        elif re.fullmatch(patron_mostrar, string):
            mostrar(string)
        elif re.fullmatch(patron_if, string):
            analisis_if(string)
        elif re.fullmatch(patron_else, string):
            analisis_else(string)
        elif re.fullmatch(patron_llave, string):
            analisis_llave(string)
        else:
            if re.fullmatch(patron_linea_vacia,string):
                return 
            
            sys.exit(f"La línea {linea} no está bien escrita: {string.strip()}")
    else:
        if lineas_if(string):
            #pila_if.append(False)
            contador_if += 1
        if re.fullmatch(patron_else, string):
            analisis_else(string)
        elif re.match(patron_llave, string):
            analisis_llave(string)
            
def lineas_if(string):
    '''
    ***
    Parametro 1: string : str
    ***
    Tipo de Retorno: match : re.Match o None
    ***
    Breve descripción de la función y el retorno:
    Comprueba si la línea es un condicional 'if' válido según el patrón especificado.
    Retorna el objeto Match si coincide, o None si no es un 'if'.
    '''
    return re.match(patron_if, string)



def analisis_if(string):
    '''
    ***
    Parametro 1: string : str
    ***
    Tipo de Retorno: None
    ***
    Breve descripción de la función y el retorno:
    Valida si esta bien escrita la linea. verifica si la condición esta definida o no y finaliza la ejecución dependiendo
    si esta definida la variable. Además evalua la condición y se añade a una cola 
    '''
    global bandera
    global contador_if
    validacion = re.match(patron_if, string)

    if validacion:
       
        nombre_variable = validacion.group(1)

        if nombre_variable not in memoria_variables:
            sys.exit(f"La variable {nombre_variable} no ha sido definida")

        valor = memoria_variables[nombre_variable]
        pila_if.append(valor)
        contador_if += 1

        if not valor:
            bandera = False
    else:
        sys.exit(f"La línea no está bien escrita: {string.strip()}")


def analisis_else(string):
    '''
    ***
    Parametro 1: string : str
    ***
    Tipo de Retorno: None
    ***
    Breve descripción de la función y el retorno:
    Valida la línea de tipo 'else' y ajusta bandera para la logica de los condicionales.
    '''
    global bandera
    global pila_if
    global contador_else

    

    if re.fullmatch(patron_else, string):
        
        if pila_if and not pila_if[-1]:
            bandera = True
        else:
            bandera = False
        contador_else += 1
    else:
        sys.exit(f"La línea {linea} no está bien escrita: {string.strip()}")
        

def analisis_llave(string):
    '''
    ***
    Parametro 1: string : str
    ***
    Tipo de Retorno: None
    ***
    Breve descripción de la función y el retorno:
    Verifica si la línea es una llave de cierre '}'. Además ajusta la pila y la variable bandera para saber si vamos a 
    leer las siguientes lineas
    '''
    
    global bandera
    global contador_llave
    
    if re.fullmatch(patron_llave, string):
    
        if pila_if:
            pila_if.pop()
        if pila_if and not pila_if[-1]:
            bandera = False
        else:
            bandera = True
        contador_llave += 1
    





def declaracion(string):
    '''
    ***
    Parametro 1: string : str
    ***
    Tipo de Retorno: None
    ***
    Breve descripción de la función y el retorno:
    Valida si la línea corresponde a una declaración. Finaliza la ejecución si la variable ya ha sido definida.
    Si no es asi agrega la variable al diccionario con valor none
    '''
    patron_declaracion = rf'\s*DEFINE\s+({patron_variable})\s*'
    validacion = re.fullmatch(patron_declaracion, string)
    if validacion:
        nombre_variable = validacion.group(1)
        if nombre_variable in memoria_variables:
            sys.exit(f"La variable {nombre_variable} ya ha sido definida")
        else:
            memoria_variables[nombre_variable] = None
    else:
        sys.exit(f"La línea {linea} no está bien escrita: {string.strip()}")



def procesar(string):
    '''
    ***
    Parametro 1: string : str
    ***
    Tipo de Retorno: None
    ***
    Breve descripción de la función y el retorno:
    Identifica que tipo de linea procesar  respectivamente, verifica si la variable ya ha sido definida, si no es asi
    ejecuta las asignaciones respectivamente dependiendo que tipo linea procesar 
    '''
    
    patron_procesar_1 = rf'\s*DP\s+({patron_variable})\s+({patron_oper_un})\s+({patron_operando})\s*'
    patron_procesar_2 = rf'\s*DP\s+({patron_variable})\s+({patron_oper_bin})\s+({patron_operando})\s+({patron_operando})\s*'
    validacion_1 = re.fullmatch(patron_procesar_1, string)
    validacion_2 = re.fullmatch(patron_procesar_2, string)

    if validacion_1:
        nombre_variable = validacion_1.group(1)
        valor = procesar_valor(validacion_1.group(3))
        if nombre_variable not in memoria_variables:
            sys.exit(f"La variable {nombre_variable} no ha sido definida")
        memoria_variables[nombre_variable] = valor
    elif validacion_2:
        nombre_variable = validacion_2.group(1)
        operador = validacion_2.group(2)
        variable_1 = validacion_2.group(3)
        variable_2 = validacion_2.group(5)

        if nombre_variable not in memoria_variables:
            sys.exit(f"La variable de nombre {nombre_variable} no ha sido definida")
        elif variable_1 not in memoria_variables:
            sys.exit(f"La variable de nombre {variable_1} no ha sido definida")
        elif variable_2 not in memoria_variables:
            sys.exit(f"La variable de nombre {variable_2} no ha sido definida")

        valor_1 = memoria_variables[variable_1]
        valor_2 = memoria_variables[variable_2]

        resultado = realizar_operacion(operador, valor_1, valor_2)
        memoria_variables[nombre_variable] = resultado
    else:
        sys.exit(f"La línea {linea} no está bien escrita: {string.strip()}")



# identifica el valor de la varable y la transforma respectivamente
def procesar_valor(valor):
    '''
    ***
    Parametro 1: valor : str
    ***
    Tipo de Retorno: int | bool | str
    ***
    Breve descripción de la función y el retorno:
    Convierte el valor de una variable en su tipo de dato correspondiente (entero, booleano o string)  y retorna el valor correspondiente
    '''
    if re.match(patron_entero, valor):
        return int(valor)
    elif re.match(patron_bool, valor):
        if valor == "True":
            return True
        else:
            return False
    elif re.match(patron_string, valor):
        return valor.replace("#", "")
    
    return valor



def realizar_operacion(operador, valor_1, valor_2):
        '''
        ***
        Parametro 1: operador : str
        Parametro 2: valor_1 : int | bool | str
        Parametro 3: valor_2 : int | bool | str
        ***
        Tipo de Retorno: int | bool | str
        ***
        Breve descripción de la función y el retorno:
        Realiza la operacion correspondiente y además de validar la compatibilidad de los tipos de datos
        respectivos a la operacion
        '''
        

        if operador == "+":
            if isinstance(valor_1,bool) or isinstance(valor_2,bool):
                sys.exit(f"La operaci´on DP o condicional en la linea {linea} es incompatible al tipo de dato.")
            elif isinstance(valor_1,str) and isinstance(valor_2,str):
                return valor_1 + valor_2
            elif isinstance(valor_1,int) and isinstance(valor_2, int):
                return valor_1 + valor_2
            else:
                if isinstance(valor_1,str):
                    valor_2 = str(valor_2)
                    return valor_1 + valor_2
                else:
                    valor_1 = str(valor_1)
                    return valor_1 + valor_2
        elif operador == "*":
            if isinstance(valor_1,bool) or isinstance(valor_2,bool):
                sys.exit(f"La operacion DP o condicional en la linea {linea} es incompatible al tipo de dato.")
            elif isinstance(valor_1,str) or isinstance(valor_2,str):
                sys.exit(f"La operacion DP o condicional en la linea {linea} es incompatible al tipo de dato.")
            else:
                return  valor_1 * valor_2
        elif operador == ">":
            if isinstance(valor_1,int) and isinstance(valor_2, int):
                if valor_1 > valor_2:
                    return True
                else:
                    return False
            sys.exit(f"La operacion DP o condicional en la linea {linea} es incompatible al tipo de dato.")
        elif operador == "==":
            #entero
            if isinstance(valor_1,int) and isinstance(valor_2, int):
                if valor_1 == valor_2:
                    return True
                else:
                    return False
            #string
            elif isinstance(valor_1,str) or isinstance(valor_2,str):
                if valor_1 == valor_2:
                    return True
                else:
                    return False
            else:
                sys.exit(f"La operacion DP o condicional en la linea {linea} es incompatible al tipo de dato.")
        else:
            
            sys.exit(f"La operacion DP o condicional en la linea {linea} es incompatible al tipo de dato.")
        


def mostrar(string):
    '''
    ***
    Parametro 1: string : str
    ***
    Tipo de Retorno: None
    ***
    Breve descripción de la función y el retorno:
    Verifica si la línea corresponde a un comando 'MOSTRAR'. Si la variable existe,
    la agrega una lista que posteriormente se usara para mostrar
    '''
    validacion = re.match(patron_mostrar, string)
    if validacion:
        nombre_variable = validacion.group(1)
        if nombre_variable in memoria_variables:
            valor = str(memoria_variables[nombre_variable])
            valores_mostrar.append(valor)
        else:
            sys.exit(f"La variable {nombre_variable} no ha sido definida")
    else:
        sys.exit(f"La línea no está bien escrita: {string.strip()}")

# Procesamiento del archivo

with open("codigo.txt", "r") as file:
    for line in file:
        detector_palabra_clave(line.strip(),linea)
        linea += 1

    if contador_if == contador_else == contador_llave:
        for i in valores_mostrar:

            with open("output.txt", "a") as archivo:
                archivo.write(i + "\n")
    else:
        sys.exit(f"La línea tipo condicional no está bien escrita")
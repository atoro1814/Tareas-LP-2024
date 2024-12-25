CC = gcc
CFLAGS = -Wall -g

all: CityDefender

CityDefender: CityDefender.o cartas.o tablero.o
	$(CC) $(CFLAGS) -o CityDefender CityDefender.o cartas.o tablero.o

CityDefender.o: CityDefender.c cartas.h tablero.h
	$(CC) $(CFLAGS) -c CityDefender.c

cartas.o: cartas.c cartas.h
	$(CC) $(CFLAGS) -c cartas.c

tablero.o: tablero.c tablero.h
	$(CC) $(CFLAGS) -c tablero.c

clean:
	rm -f *.o CityDefender

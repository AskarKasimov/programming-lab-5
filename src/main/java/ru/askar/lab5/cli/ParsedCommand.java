package ru.askar.lab5.cli;

/**
 * Record для удобного разделения команды на название и аргументы
 *
 * @param name - название команды
 * @param args - аргументы к команде
 */
public record ParsedCommand(String name, String[] args) {
}
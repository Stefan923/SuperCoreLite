package me.stefan923.supercore.database;

public final class SQLStatemet {

    public static final String CREATE_USER = "CALL CREATE_USER(?, ?);";

    public static final String DELETE_USER_BY_UUID = "CALL DELETE_USER_BY_UUID(?);";
    public static final String DELETE_USER_BY_NAME = "CALL DELETE_USER_BY_NAME(?);";

    public static final String GET_USER_BY_UUID = "SELECT * FROM `{prefix}users` WHERE `uuid` = ?;";
    public static final String GET_USER_BY_NAME = "SELECT * FROM `{prefix}users` WHERE `username` = ?;";

    private SQLStatemet() { }

}

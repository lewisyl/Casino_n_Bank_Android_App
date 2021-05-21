package dev.lewisliu.csi360midterm;

import java.io.Serializable;

public class Account implements Serializable {
    private String _bankName, _accountNum;
    private int _balance;

    public Account(String _bankName, String _accountNum, int _balance) {
        this._bankName = _bankName;
        this._accountNum = _accountNum;
        this._balance = _balance;
    }

    public void withdraw(int amount) {
        _balance -= amount;
    }

    public void deposit(int amount) {
        _balance += amount;
    }

    public int get_balance() {
        return _balance;
    }
}

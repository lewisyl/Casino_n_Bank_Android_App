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

    public Account(String _bankName, String _accountNum) {
        this._bankName = _bankName;
        this._accountNum = _accountNum;
        this._balance = 10000;
    }

    public String get_bankName() {
        return _bankName;
    }

    public String get_accountNum() {
        return _accountNum;
    }

    public int get_balance() {
        return _balance;
    }

    public void set_balance(int _balance) {
        this._balance = _balance;
    }
}

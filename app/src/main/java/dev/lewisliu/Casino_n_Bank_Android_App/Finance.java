package dev.lewisliu.Casino_n_Bank_Android_App;

import java.io.Serializable;

public class Finance implements Serializable {
    private String _bankName, _accountNum;
    private int _balance, _wallet;

    public Finance(String _bankName, String _accountNum, int _balance, int _wallet) {
        this._bankName = _bankName;
        this._accountNum = _accountNum;
        this._balance = _balance;
        this._wallet = _wallet;
    }

    public Finance(String _bankName, String _accountNum) {
        this._bankName = _bankName;
        this._accountNum = _accountNum;
        this._balance = 5000;
        this._wallet = 200;
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

    public int get_wallet() {
        return _wallet;
    }

    public void set_wallet(int _wallet) {
        this._wallet = _wallet;
    }
}

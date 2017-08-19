package com.feticankirazci.anlikdepremler.services;

import com.feticankirazci.anlikdepremler.models.Contacts;

import java.util.ArrayList;

/**
 * Created by Feti on 17.08.2017.
 */

public class ContactsListEvent extends ServiceEvent {

    public ArrayList<Contacts> mContactList;

    public ContactsListEvent(Exception exception, ArrayList<Contacts> contactList) {
        super(exception);
        this.mContactList = contactList;
    }

    public ContactsListEvent(Exception exception) {
        super(exception);
    }
}

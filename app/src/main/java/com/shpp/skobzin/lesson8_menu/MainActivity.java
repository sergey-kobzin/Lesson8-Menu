package com.shpp.skobzin.lesson8_menu;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MenuInflater menuInflater;
    private ColoredFragment topLeftFragment;
    private ColoredFragment topRightFragment;
    private ColoredFragment bottomFragment;
    private int selectedFragmentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        menuInflater = getMenuInflater();

        topLeftFragment = (ColoredFragment) fragmentManager.findFragmentById(R.id.topLeftFragment);
        topRightFragment = (ColoredFragment) fragmentManager.findFragmentById(R.id.topRightFragment);
        bottomFragment = (ColoredFragment) fragmentManager.findFragmentById(R.id.bottomFragment);

        topLeftFragment.setColor(R.color.color4Red);
        topRightFragment.setColor(R.color.color2Green);
        bottomFragment.setColor(R.color.color1Blue);

        registerForContextMenu(topLeftFragment.getView());
        registerForContextMenu(topRightFragment.getView());
        registerForContextMenu(bottomFragment.getView());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuTopLeftFragment:
                switchFragmentVisibility(menuItem, topLeftFragment);
                return true;
            case R.id.menuTopRightFragment:
                switchFragmentVisibility(menuItem, topRightFragment);
                return true;
            case R.id.menuBottomFragment:
                switchFragmentVisibility(menuItem, bottomFragment);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menuInflater.inflate(R.menu.menu_context, menu);

        int currentColor = 0;
        int firstDisabledColor = 0;
        int secondDisabledColor = 0;
        selectedFragmentID = view.getId();
        switch (selectedFragmentID) {
            case R.id.topLeftFragment:
                currentColor = topLeftFragment.getColor();
                firstDisabledColor = topRightFragment.getColor();
                secondDisabledColor = bottomFragment.getColor();
                break;
            case R.id.topRightFragment:
                currentColor = topRightFragment.getColor();
                firstDisabledColor = topLeftFragment.getColor();
                secondDisabledColor = bottomFragment.getColor();
                break;
            case R.id.bottomFragment:
                currentColor = bottomFragment.getColor();
                firstDisabledColor = topLeftFragment.getColor();
                secondDisabledColor = topRightFragment.getColor();
                break;
        }
        modifyContextMenu(menu, currentColor, firstDisabledColor, secondDisabledColor);
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (selectedFragmentID) {
            case R.id.topLeftFragment:
                changeFragmentColor(menuItem, topLeftFragment);
                return true;
            case R.id.topRightFragment:
                changeFragmentColor(menuItem, topRightFragment);
                return true;
            case R.id.bottomFragment:
                changeFragmentColor(menuItem, bottomFragment);
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    private void switchFragmentVisibility(MenuItem menuItem, ColoredFragment fragment) {
        View fragmentView = fragment.getView();
        if (menuItem.isChecked()) {
            fragmentView.setVisibility(View.INVISIBLE);
            menuItem.setChecked(false);
        } else {
            fragmentView.setVisibility(View.VISIBLE);
            menuItem.setChecked(true);
        }
    }

    private void changeFragmentColor(MenuItem menuItem, ColoredFragment fragment) {
        switch (menuItem.getItemId()) {
            case R.id.menuBlack:
                fragment.setColor(R.color.color0Black);
                break;
            case R.id.menuBlue:
                fragment.setColor(R.color.color1Blue);
                break;
            case R.id.menuGreen:
                fragment.setColor(R.color.color2Green);
                break;
            case R.id.menuCyan:
                fragment.setColor(R.color.color3Cyan);
                break;
            case R.id.menuRed:
                fragment.setColor(R.color.color4Red);
                break;
            case R.id.menuPurple:
                fragment.setColor(R.color.color5Purple);
                break;
            case R.id.menuYellow:
                fragment.setColor(R.color.color6Yellow);
                break;
            case R.id.menuWhite:
                fragment.setColor(R.color.color7White);
                break;
        }
    }

    private void modifyContextMenu(ContextMenu menu, int currentColor, int firstDisabledColor, int secondDisabledColor) {
        menu.getItem(currentColor - R.color.color0Black).setChecked(true);
        menu.getItem(firstDisabledColor - R.color.color0Black).setVisible(false);
        menu.getItem(secondDisabledColor - R.color.color0Black).setVisible(false);
    }
}

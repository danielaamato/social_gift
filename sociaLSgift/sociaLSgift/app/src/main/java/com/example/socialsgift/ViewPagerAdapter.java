package com.example.socialsgift;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Fragments.GiftFragment;
import Fragments.MenuFragment;
import Fragments.ProfileFragment;
import Fragments.WishlistFragment;

public class ViewPagerAdapter extends FragmentStateAdapter
{
    private final String accessToken; // Variable para almacenar el accessToken

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String accessToken)
    {
        super(fragmentActivity);
        this.accessToken = accessToken; // Almacenar el accessToken
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        switch (position)
        {
            case 0:
                return MenuFragment.newInstance(accessToken);
            case 1:
                // Crea y devuelve una instancia de GiftFragment (puedes pasar accessToken si también lo necesita)
                return new GiftFragment();
            case 2:
                // Crea y devuelve una instancia de WishlistFragment (puedes pasar accessToken si también lo necesita)
                return new WishlistFragment();
            case 3:
                // Crea y devuelve una instancia de ProfileFragment (puedes pasar accessToken si también lo necesita)
                return new ProfileFragment();
            default:
                // Por defecto, devuelve una nueva instancia de MenuFragment con accessToken
                return MenuFragment.newInstance(accessToken);
        }
    }

    @Override
    public int getItemCount()
    {
        return 4;
    }
}

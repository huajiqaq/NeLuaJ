package com.androlua.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<List<String>> mChildData;

    private final List<String> mGroupData;

    private final int mGroupLayout;

    private final int mChildLayout;

    private final LayoutInflater mLayoutInflater;

    private boolean mNotifyOnChange = true;

    public ArrayExpandableListAdapter(Context context) {
        this(context, new ArrayList<String>(), android.R.layout.simple_expandable_list_item_1, new ArrayList<List<String>>(), android.R.layout.simple_expandable_list_item_1);
    }

    public ArrayExpandableListAdapter(Context context, List<String> groupData, List<List<String>> childData) {
        this(context, groupData, android.R.layout.simple_expandable_list_item_1, childData, android.R.layout.simple_expandable_list_item_1);
    }

    public ArrayExpandableListAdapter(Context context, List<String> groupData, int groupLayout, List<List<String>> childData, int childLayout) {
        mGroupData = groupData;
        mGroupLayout = groupLayout;
        mChildData = childData;
        mChildLayout = childLayout;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getGroupCount() {
        // TODO: Implement this method
        return mGroupData.size();
    }

    @Override
    public int getChildrenCount(int p1) {
        // TODO: Implement this method
        return mChildData.get(p1).size();
    }

    @Override
    public Object getGroup(int p1) {
        // TODO: Implement this method
        return mGroupData.get(p1);
    }

    @Override
    public Object getChild(int p1, int p2) {
        // TODO: Implement this method
        return mChildData.get(p1).get(p2);
    }

    @Override
    public long getGroupId(int p1) {
        // TODO: Implement this method
        return p1;
    }

    @Override
    public long getChildId(int p1, int p2) {
        // TODO: Implement this method
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        // TODO: Implement this method
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO: Implement this method
        if (convertView == null)
            convertView = mLayoutInflater.inflate(mGroupLayout, parent, false);
        ((TextView) convertView).setText(mGroupData.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO: Implement this method
        if (convertView == null)
            convertView = mLayoutInflater.inflate(mChildLayout, parent, false);
        ((TextView) convertView).setText(mChildData.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int p1, int p2) {
        // TODO: Implement this method
        return true;
    }

    public void add(String groutData, List<String> childData) {
        mGroupData.add(groutData);
        mChildData.add(childData);
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void add(String groutData, String[] childData) {
        mGroupData.add(groutData);
        mChildData.add(Arrays.asList(childData));
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }
}

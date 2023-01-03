package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        /**
         * �� current �������� previous, �������� currentMinusPrevious - �������� ����������� ��� ���������� ��������
         */
        Set<User> currentMinusPrevious = new HashSet<>();
        currentMinusPrevious.addAll(current);
        currentMinusPrevious.removeAll(previous);

        /**
         * �� previous �������� current, �������� previousMinusCurrent - �������� ��������� ��������
         */
        Set<User> previousMinusCurrent = new HashSet<>();
        previousMinusCurrent.addAll(previous);
        previousMinusCurrent.removeAll(current);


        /**
         * � ���������� �������� ������ ����� � ������� ������
         */
        Map<Integer, String> previosMap = new HashMap<Integer, String>();
        Map<Integer, String> currentMap = new HashMap<Integer, String>();
        Map<Integer, String> currentMinusPreviosMap = new HashMap<Integer, String>();
        Map<Integer, String> previousMinusCurrentMap = new HashMap<Integer, String>();

        /**
         * ��� previous �������� � ���� previosMap,
         * � ���������� ��� ������� ������ ���������� ������� ��� �����������
         */
        Iterator<User> itPrevious = previous.iterator();
        while (itPrevious.hasNext()) {
            User user  = itPrevious.next();
            previosMap.put(user.getId(), user.getName());
        }

        /**
         * ��� current �������� � ���� currentMap,
         * � ���������� ��� ������� ������ ��������� �� �������
         */
        Iterator<User> itCurrent = current.iterator();
        while (itCurrent.hasNext()) {
            User user  = itCurrent.next();
            currentMap.put(user.getId(), user.getName());
        }


        /**
         * ��� currentMinusPrevious �������� � ���� currentMinusPreviosMap,
         * � ���������� ��� ������� ������ ���������� ������� ��� �����������
         */
        Iterator<User> itCurrentMinusPrevious = currentMinusPrevious.iterator();
        while (itCurrentMinusPrevious.hasNext()) {
            User user  = itCurrentMinusPrevious.next();
            currentMinusPreviosMap.put(user.getId(), user.getName());
        }
        /**
         * ��� previousMinusCurrent �������� � ���� previousMinusCurrentMap,
         * � ���������� ��� ������� ����� ��������� �� �������
         */
        Iterator<User> itPreviousMinusCurrent = previousMinusCurrent.iterator();
        while (itPreviousMinusCurrent.hasNext()) {
            User user  = itPreviousMinusCurrent.next();
            previousMinusCurrentMap.put(user.getId(), user.getName());
        }


        /**
         * �������� ���������� ����������� � ���������� ���������
         */
        Iterator<Map.Entry<Integer, String>> itr = currentMinusPreviosMap.entrySet().iterator();
        while (itr.hasNext()) {
            Integer key = itr.next().getKey();
            if (previosMap.containsKey(key) && !previosMap.get(key).equals(currentMinusPreviosMap.get(key))) {
                changed++;
            }
            if (!previosMap.containsKey(key)) {
                added++;
            }
        }

        /**
         * �������� ���������� ��������� ���������
         */
        Iterator<Map.Entry<Integer, String>> itr2 = previousMinusCurrentMap.entrySet().iterator();
        while (itr2.hasNext()) {
            Integer key = itr2.next().getKey();
            if (!currentMap.containsKey(key)) {
                deleted++;
            }
        }

        return new Info(added, changed, deleted);
    }

}
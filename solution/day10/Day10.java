package solution.day10;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import skeleton.AdventOfCode;

public class Day10 extends AdventOfCode{

	public Day10() throws FileNotFoundException {
		super();
	}
	
	public Day10(String test) throws FileNotFoundException {
		super(" ");
	}
	
	@Override
	public void part1() {
		int id1 = 61;
        int id2 = 17;

        String lines[] = this.getInput().split("\n");
        Map<Integer, Bot> bots = new HashMap<>();
        List<Instruction> instructions = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split(" ");
            if (line.contains("value")) {
                instructions.add(new Instruction(getValue(split, 5), getValue(split, 1)));
            } else {
                int botId = Integer.parseInt(split[1]);
                Bot bot = bots.get(botId);
                if (bot == null) {
                    bot = new Bot(botId);
                }
                bot.setAction(getValue(split, 6), getValue(split, 11));
                bots.put(botId, bot);
            }
        }
        List<Integer> output = new ArrayList<>();
        Map<Integer, Set<Integer>> hasHandled = new HashMap<>();
        for (Instruction instruction : instructions) {
            bots.get(instruction.botId).addValue(instruction.value);
            while (true) {
                final boolean[] hasPerformed = {false};
                bots.values().stream()
                        .filter(bot -> bot.getValues().size() == 2)
                        .forEach(bot -> {
                            hasPerformed[0] = true;
                            int lowValue = bot.getLowValue();
                            int highValue = bot.getHighValue();

                            Set<Integer> handled = hasHandled.get(bot.getBotId());
                            if (handled == null) {
                                handled = new HashSet<>();
                            }
                            handled.add(lowValue);
                            handled.add(highValue);
                            hasHandled.put(bot.getBotId(), handled);

                            if (bot.getLowId() > -1) {
                                bots.get(bot.getLowId()).addValue(lowValue);
                            } else if (bot.getLowId() > -4) {
                                output.add(lowValue);
                            }
                            if (bot.getHighId() > -1) {
                                bots.get(bot.getHighId()).addValue(highValue);
                            } else if (bot.getHighId() > -4) {
                                output.add(highValue);
                            }
                            bot.clear();
                        });
                if (!hasPerformed[0]) {
                    break;
                }
            }
        }

        Integer key = hasHandled.entrySet()
                .stream().filter(k ->
                        k.getValue().contains(id1)
                                && k.getValue().contains(id2))
                .findFirst()
                .get().getKey();
        System.out.println(key);
        System.out.println(output.stream().reduce(1, (a, b) -> a * b));
	}
	
public static class Bot {
		private final int botId;
	    List<Integer> values = new ArrayList<>();

	    int lowId;
	    int highId;

	    public Bot(int botId) {
	        this.botId = botId;
	    }

	    public int getBotId() {
	        return botId;
	    }

	    public Bot addValue(int value) {
	        values.add(value);
	        return this;
	    }

	    public Bot setAction(int lowId, int highId) {
	        this.lowId = lowId;
	        this.highId = highId;
	        return this;
	    }

	    public List<Integer> getValues() {
	        return values;
	    }

	    public int getLowValue() {
	        if (values.get(0) > values.get(1)) {
	            return values.get(1);
	        }
	        return values.get(0);
	    }

	    public int getHighValue() {
	        if (values.get(0) > values.get(1)) {
	            return values.get(0);
	        }
	        return values.get(1);
	    }

	    public int getLowId() {
	        return lowId;
	    }

	    public int getHighId() {
	        return highId;
	    }

	    public void clear() {
	        this.values.clear();
	    }
}

	@Override
	public void part2() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean checkInstruction(String instruction) {
		return (instruction.startsWith("bot") ? true : false);
	}
	
	public static class Instruction {
		int botId;
        int value;

        public Instruction(int botId, int value) {
            this.botId = botId;
            this.value = value;
        }
    }

    private int getValue(String[] split, int i) {
        int i1 = Integer.parseInt(split[i]);
        if (split[i - 1].equals("output")) {
            return -i1 - 1; //Setting -1 in case of output 0
        } else {
            return i1;
        }
	}
}

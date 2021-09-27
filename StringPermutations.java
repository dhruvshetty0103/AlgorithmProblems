package com.algorithmproblems.stringpermutations;
import java.util.*;
import java.util.concurrent.RecursiveAction;
/* Java program to print all the permutations
 * of the given string
 */
public class StringPermutation
{
	public static ArrayList<String> recursiveCatch=new ArrayList<String>();
	public static ArrayList<String> iterativeCatch=new ArrayList<String>();
	//--------------------------ITERATIVE-----------------------------------
	// Utility function to swap the characters in a character array
    private static void swap(char[] arr, int i, int j)
    {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }
    // Utility function to reverse a char array between specified indices
    private static void reverse(char[] arr, int i, int j)
    {
        // do till two end points intersect
        while (i < j) {
            swap(arr, i++, j--);
        }
    }
    // Iterative function to find permutations of a string in Java
    public static void iterativePermutations(String str)
    {
        // base case
        if (str == null || str.length() == 0)
        {
            return;
        }

        // base case
        if (str.length() == 1) {
            System.out.print(str);
            return;
        }

        // sort the string in a natural order
        char[] chars = str.toCharArray();
        Arrays.sort(chars);

        // get length of the string
        int n = str.length();

        while (true)
        {
            // print the current permutation
        	iterativeCatch.add(String.valueOf(chars));
            //System.out.print(String.valueOf(chars)+" ");

            /* The following code will rearrange the string to the next
               lexicographically ordered permutation (if any) or return if
               we are already at the highest possible permutation */

            // Find the largest index `i` such that `chars[i-1]` is less than `chars[i]`
            int i = n - 1;
            while (chars[i-1] >= chars[i])
            {
                // if `i` is the first index of the string, we are
                // already at the last possible permutation
                // (string is sorted in reverse order)
                if (--i == 0)
                {
                    return;
                }
            }

            // find the highest index `j` to the right of index `i` such that
            // `chars[j] > chars[i-1]` (`chars[i…n-1]` is sorted in reverse order)

            int j = n - 1;
            while (j > i && chars[j] <= chars[i-1])
                j--;
            // swap character at index `i-1` with index `j`
            swap(chars, i - 1, j);
            // reverse substring `chars[i…n-1]`
            reverse (chars, i, n - 1);
        }
    }

	//----------------------------RECURSIVE---------------------------------------
	// Function to print all the distinct permutations of str
	static void printDistinctPermutn(String str,String ans)
	{
		// If string is empty
		if (str.length() == 0)
		{
			// print ans
			recursiveCatch.add(ans);
			//System.out.print(ans+"");
			return;
		}
		/* Make a boolean array of size '26' which
		 * stores false by default and make true
		 * at the position which alphabet is being used
		 */
		boolean alpha[] = new boolean[26];
		for (int i = 0; i < str.length(); i++)
		{
			//Last character of string
			char ch = str.charAt(i);
			// Rest of the string after excluding the ith character
			String ros = str.substring(0, i) +
					str.substring(i + 1);

			/* If the character has not been used
			 * then recursive call will take place.
			 * Otherwise, there will be no recursive call
			 */
			if (alpha[ch - 'a'] == false)
				printDistinctPermutn(ros, ans + ch);
			alpha[ch - 'a'] = true;
		}
	}

	// Driver code
	public static void main(String[] args)
	{
		Scanner reader=new Scanner(System.in);
		System.out.println("Enter string in lower case to find it's permutation:");
		String input=reader.next();

		printDistinctPermutn(input.toLowerCase(),"");
		iterativePermutations(input.toLowerCase());
		System.out.print("\nPermutations obtained by Recursive Method"+recursiveCatch);
		System.out.print("\nPermutations obtained by Iterative Method"+iterativeCatch);
		if(recursiveCatch.equals(iterativeCatch))
			System.out.println("\nBoth Iterative and Recursive method gives same set of permutation");
		reader.close();
	}
}

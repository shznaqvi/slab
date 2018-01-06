package edu.aku.hassannaqvi.slab.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import edu.aku.hassannaqvi.slab.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;

/**
 * Created by Hassan.naqvi on 11/23/2017.
 */

public class FormsProvider extends ContentProvider {

    public static final String AUTHORITY = "edu.aku.hassannaqvi.slab.providers";
    /**
     * Tag for the log messages
     */
    public static final String TAG = FormsProvider.class.getSimpleName();
    /**
     * UriMatcher declaration, matches incoming URIs with appropriate constants
     */
    public static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    /**
     * The match code for some items in the Forms table.
     */
    private static final int CODE_FORMS_ALL = 1;
    /**
     * The match code for an item in the Forms table.
     */
    private static final int CODE_FORMS_SINGLE = 2;

    /** Add possible URIs to handle by the matcher */
    static {
        MATCHER.addURI(AUTHORITY, FormsTable.TABLE_NAME, CODE_FORMS_ALL);
        MATCHER.addURI(AUTHORITY, FormsTable.TABLE_NAME + "/#", CODE_FORMS_SINGLE);
    }

    /**
     * DatabaseHelper - database helper object
     */
    private DatabaseHelper mDbHelper;

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable db
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = MATCHER.match(uri);
        switch (match) {
            case CODE_FORMS_ALL:
                cursor = db.query(FormsTable.TABLE_NAME, projection, null, null,
                        null, null, sortOrder);
                break;
            case CODE_FORMS_SINGLE:
                // Where
                selection = FormsTable._ID + "=?";
                // What
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                // Form query to the db
                cursor = db.query(FormsTable.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);

                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        long id = -1;
        switch (MATCHER.match(uri)) {

            case CODE_FORMS_ALL:
                id = db.insert(FormsTable.TABLE_NAME,
                        null, contentValues);
                break;

            case CODE_FORMS_SINGLE:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        if (id == -1) {
            Toast.makeText(getContext(), "Failed to insert row for " + uri, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Failed to insert row for " + uri);
            return null;
        }

        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_FORMS_ALL:
                return "vnd.android.forms.dir/" + AUTHORITY + "." + FormsTable.TABLE_NAME;
            case CODE_FORMS_SINGLE:
                return "vnd.android.forms.item/" + AUTHORITY + "." + FormsTable.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}
